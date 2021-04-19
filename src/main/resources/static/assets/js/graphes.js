$('document').ready(function () {
    let url = 'https://ptutpenibilite.herokuapp.com/';
    let idPiece =  window.location.href.split('=')[1];
    let typesDataAjax;
    let capteursDataAjax;

    // Recupère les donnée de l'api
    $.get(url+ 'api/piece/getTypeCapteur',{id : idPiece},(data)=>{
        typesDataAjax = data.types;
    }).done(()=>{
        $.get(url+ 'api/piece/getDonnees',{id : idPiece},(data)=>{
            capteursDataAjax = data;
        }).done(initGraphs);
    });

    // Doughnut chart
    $.get(url + 'api/type/depassement',{id : idPiece}).done((dataJson)=>{
        console.log(dataJson);
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

        var options = {
            responsive: true,
            animation: {
                animateScale: true,
                animateRotate: true
            }
        };

        let config = {
            type: 'doughnut',
            data: data,
            options: options
        };

        let doughnutChart = new Chart( $('#doughnutChart'), config);


    });

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

            /*let options = {
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
            };*/

            let config = {
                type: 'line',
                data: data,
              //  options : options

            };

            new Chart( $('#chart'+myType), config);
        }
    }
});