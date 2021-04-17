let url = 'http://localhost:8080/';

function deleteType(id) {
    if (confirm('êtes-vous sûr de vouloir supprimer ce type ?' +id)){

        $.ajax({
            url: url + 'api/type/delete' ,
            data : {
                id : id
            },
            method: 'DELETE'
        })
        .done((data)=> {
            if(data.status === 0){
                window.location.reload();
            }else  {
                document.getElementById("msg").innerHTML = "Vous ne pouvez pas supprimer ce type car des capteurs y sont associés.";
            }
        })
        .fail((data) => {
            console.log("fail" , data);
        })

    }
}

function deleteCapteur(id) {
    if (confirm('êtes-vous sûr de vouloir supprimer ce type ?' +id)){

        $.ajax({
            url: url + 'api/capteur/delete' ,
            data : {
                id : id
            },
            method: 'DELETE'
        })
            .done((data)=> {
                if(data.status === 0){
                    window.location.reload();
                }else  {
                    document.getElementById("msg").innerHTML = "Vous ne pouvez pas supprimer ce capteur car des mesures y sont associées.";
                }
            })
            .fail((data) => {
                console.log("fail" , data);
            })

    }
}


