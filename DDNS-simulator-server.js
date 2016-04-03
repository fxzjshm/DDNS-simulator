function getIP(objectId){
    AV.initialize('CaiXYnmy3BPpcpVE9eRaKQkV-gzGzoHsz', '7tryYPnSzMGwpivDanO2yfG0');
    var IP_User_Obj = AV.Object.extend('IP_User_Obj');
    var ip = new IP_User_Obj();
    var query = new AV.Query(IP_User_Obj);
    query.get(objectId).then(function(ip) {
            return "IP: " + ip.get('IP');
        }, function(error) {
            return "Error: " + error;
    });
}
