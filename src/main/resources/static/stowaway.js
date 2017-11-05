
function getUuid() {
    var s = [];
    var hexDigits = "0123456789abcdef";
    for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
    s[8] = s[13] = s[18] = s[23] = "-";

    var uuid = s.join("");
    return uuid.substr(0,8);
}

var stowaway = function() {
    var uuid = getUuid();
    var s = document.getElementById('text').value.trim();
    var transize = document.getElementById('transize').value;
    var tmp="";
    while(s.length > 0){
        if(s.length > transize){
            tmp = s.substr(0,transize);
            s = s.substr(transize,s.length-transize);
        }else{
            tmp = s;
            s = "";
        }
        $.ajax({
            url:"stow",
            async:false,
            data:{"uuid":uuid,"str":tmp},
            success:function (e) {
                console.log(e);
                $("#info").html("保存成功！id= ");
                $("#str").html(uuid);
            },
            error:function () {
                return;
            }
        })
    }
    $.ajax({
        url:"stow",
        async:false,
        data:{"uuid":uuid,"str":"$"},
        success:function (e) {
            console.log(e);
        },
        error:function () {
            return;
        }
    })
};

var unload = function () {
    var s = document.getElementById('text').value.trim();
    $.ajax({
        url:"unload",
        async:false,
        data:{"uuid":s},
        success:function (e) {
            console.log(e);
            $("#info").html("取货成功！str= ");
            $("#str").html(e);
        },
        error:function () {
            return;
        }
    })
}
