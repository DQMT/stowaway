function pop(s) {
    if(typeof  s =="string" && s.toString().length>5){
        var newStr= s.substr(0,5);
        return newStr;
    }else{
        return "";
    }
}
function stowaway() {
    var s = document.getElementById('text').value.trim();
    var tmp="";
    while(s.length > 0){
        if(s.length > 5){
            tmp = s.substr(0,5);
            s = s.substr(5,s.length-5);
        }else{
            tmp = s;
            s = "";
        }
        console.log("s=" + s);
        console.log("post"+tmp);
        $.ajax({
            url:"stow",
            async:false,
            data:tmp,
            success:function () {

            },
            error:function () {
                return;
            }
        })
    }


}