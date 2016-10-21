
function initialise() {

    document.getElementById("btnStart")
            .addEventListener('click', function() {
                var nom = document.getElementById('nom').value;
                setInterval(getSequenceFct(nom), 2000);
            });
    todoManager = initTodoManager();

    todoManager.fctPublique1();
    todoManager.pub1 = 15;
    todoManager.fctPublique1();

    //todoManager.fpriv();
    console.log(todoManager.priv1);
    todoManager.priv1 =  'hacked!!!!';
    console.log(todoManager.priv1);
    todoManager.fctPublique1();
};

function getSequenceFct(nom) {
    var sequenceName = nom;
    var compteur = 1;
    return function() {
        console.log("sequence " + sequenceName + " cpt: " + compteur);
        compteur++;
    };
};


function initTodoManager() {
    var priv1 = 42;
    var fpriv = function() {
        console.log("fonction privé de todoManager");
    };

    // ce qui est déclaré dans todoManager est 'public'
    var todoManager = {
        "fctPublique1" : function() {
            console.log("pub1 : " + this.pub1 + " priv1 : " + priv1);
            fpriv();
        },
        "pub1" : 1
    };

    return todoManager;
};