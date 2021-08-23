function f_check(){
    if(document.form.checkbutton.checked===true){
        document.form.textbox.disabled = false;
    }
    else if(document.form.checkbutton.checked===false){
        document.form.textbox.disabled = true;
    }
}

