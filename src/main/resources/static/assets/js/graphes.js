$('document').ready(function () {
    let url = 'https://ptutpenibilite.herokuapp.com/';
    let idPiece =  window.location.href.split('=')[1];
    let typesDataAjax;
    let capteursDataAjax;

    initGraphToday();

    // click formulaire date début & fin
    $('#btnAfficherGraph').click(()=>{
        let debut = $('#debut').val();
        let fin = $('#fin').val();
        $('#msgEtat').text("");

        if (debut<=fin){
            getDataAPI(debut, fin);
        }else{
            $('#msgEtat').text( "La date de début doit être inférieur à la date de fin");
        }
    });

    // Initialisation des graphes à la date d'aujourd'hui
    function initGraphToday(){
        let today = new Date();
        let month = today.getMonth();
        let day = today.getDate();
        let date;

        if (month<10){
            if (month == 0){
                month = '1';
            }else{
                month = '0'+parseInt(month+1);
            }
        }else {
            month = parseInt(month+1);
        }

        if (today.getDate()<10){
            day = '0'+today.getDate();
        }else {
            day = today.getDate()
        }

        date = today.getFullYear()+'-'+month+'-'+day;

        $('#debut').val(date);
        $('#fin').val(date);

        getDataAPI(date, date);
    }

    // Recupère les données de l'api
    function getDataAPI(dateDebut, dateFin){
        $.get(url+ 'api/piece/getTypeCapteur',{id : idPiece},(data)=>{ typesDataAjax = data.types;})
            .done(()=>{
                $.get(url+ 'api/piece/getDonnees',{id : idPiece, dateMin: dateDebut, dateMax: dateFin},(data)=>{
                    capteursDataAjax = data;
                })
                    .done(initGraphs);
            });

        $.get(url + 'api/type/depassement',{id : idPiece, dateMin: dateDebut, dateMax: dateFin}).done((dataJson)=>{initDoughnutChart(dataJson)});
    }

    // Doughnut chart
    function initDoughnutChart(dataJson) {
        let labels = [];
        let dataCapteur = [];
        let colors = [];

        $.each(dataJson, function (key,capteur) {
            labels.push(capteur.libelle);
            dataCapteur.push(capteur.depassement);
            colors.push('#'+ ('000000' + Math.floor(Math.random()*16777215).toString(16)).slice(-6));
        });

        let data = {
            labels: labels,
            datasets: [{
                label: 'Taux de pénibilité',
                data: dataCapteur,
                backgroundColor: colors,
                hoverOffset: 4
            }]
        };

        let options = {
            responsive: true,
            maintainAspectRatio : false,
        };

        let config = {
            type: 'doughnut',
            data: data,
            options: options
        };

        let doughnutChart = Chart.getChart('doughnutChart');

        if (doughnutChart !== undefined){
            doughnutChart.destroy();
        }

        doughnutChart = new Chart( $('#doughnutChart'), config);
        doughnutChart.canvas.parentNode.style.height = '300px';
    }

    //Line chart
    function initGraphs() {
        // Création d'un graphe par type de capteur
        for (let t = 0; t<typesDataAjax.length; t++){
            let myType = typesDataAjax[t];
            let datesForG = [];
            let dataForG=[];
            let sMax;
            let sMin;
            let unite;

            // Parcours des capteurs de la pièce
            $.each(capteursDataAjax, function (key,capteur) {

                // une courbe par capteur
                if (capteur.type == myType){
                    let randomColor = '#'+ ('000000' + Math.floor(Math.random()*16777215).toString(16)).slice(-6);
                    let dates = capteur.dates;
                    let dataOfC = [];

                    // Parcours des mesures faites par un capteur
                    for (let i = 0; i<dates.length ; i++){
                        if (!datesForG.includes(dates[i])){
                            datesForG.push(dates[i]);
                        }
                        dataOfC.push({x: dates[i], y: capteur.valeurs[i]})
                    }

                    let dataCapteur = {
                        label : capteur.libelle,
                        data : dataOfC,
                        fill : false,
                        borderColor : randomColor,
                        backgroundColor : 'rgba(153, 102, 255, 0.5)',
                        tension: 0.1
                    };
                    dataForG.push(dataCapteur);

                    if (sMax === undefined && sMin === undefined && unite === undefined){
                        sMin = capteur.seuilMin;
                        sMax = capteur.seuilMax;
                        unite = capteur.unite;
                    }
                }

            });

            let data = {
                labels: datesForG,
                datasets: dataForG
            };

            // Ajout lignes seuil Max et Min

            let plugins = {
                plugins: {
                    autocolors: false,
                    annotation: {
                        annotations: {
                            line1: {
                                drawTime: 'afterDatasetsDraw',
                                type: 'line',
                                xMin: 0,
                                xMax: 100000000000,
                                yMin: sMin,
                                yMax: sMin,
                                backgroundColor: 'rgba(255, 0, 0, 0.5)'
                            },
                            line2: {
                                drawTime: 'afterDatasetsDraw',
                                type: 'line',
                                xMin: 0,
                                xMax: 100000000000,
                                yMin: sMax,
                                yMax: sMax,
                                backgroundColor: 'rgba(255, 0, 0, 0.5)'
                            }
                        }
                    }
                }
            };

            let config = {
                type: 'line',
                data: data,
                plugins

            };

            let chart = Chart.getChart('chart'+myType);

            if (chart !== undefined){
                chart.destroy();
            }
            chart = new Chart( $('#chart'+myType), config);
            //chart.register({id : 'annotation'});
        }
    }
});