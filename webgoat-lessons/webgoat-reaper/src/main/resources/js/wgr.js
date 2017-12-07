function checkCheatCode(context){
    var cheatCode = null;
    if(context === 1){
        cheatCode = "7184321d12575f08afe593a778142909";//sqlInjections
    } else if(context === 2){
        cheatCode = "caa2bf948d7baa58db081cef58cfe7fa";//authBypasses
    } else if(context === 3){
        cheatCode = "2c71e977eccffb1cfb7c6cc22e0e7595";//xss
    } else if(context === 4){
        cheatCode = "4693f404c66c2bd33a4e0ba4c7a00cd8";//accessControl
    } else if(context === 5){
        cheatCode = "c1e7dec397b677c6c2e65405f6e617bd";//insecureComm
    } else if(context === 6){
        cheatCode = "ca969a1bc97732d97b1e88ce8396c216";//csrf
    } else if(context === 7){
        cheatCode = "7fb60b28a6c3804e52c5929637d374a4";//clientSideFiltering
    }

    var cheatCodeEntered = $('#cheat-code-'+context).val();

    if(cheatCodeEntered === cheatCode){
        $('#correction-'+context).show();
        alert('Solution débloquée !');
    } else {
        alert('Code incorrect !');
    }
}