let detailApp = new Vue({
    el:"#detailApp",
    data:{
        diary:null,
        userId:null
    },
    methods:{
       loadDetail:function (){
           let id = window.location.href.substring(window.location.href.lastIndexOf('?')+1);
           if (!id||isNaN(id)){
               alert("非法访问！参数不足！")
               location.href="/index.html";
               return ;
           }
            $.ajax({
                url:"/api/v1/diary/getById/"+id,
                success:function (json){
                    if (json.state==2000){
                        detailApp.diary = json.data
                    }else {
                        location.href="/index.html";
                    }
                }
            })
       },
        getUserInfo:function (){
            $.ajax({
                url:"/api/v1/user/userInfo",
                success:function (json){
                    detailApp.userId = json.id;
                }
            })
        }
    },
    created:function (){
        this.loadDetail();
        this.getUserInfo();
    }
});
function deleteDiary(a){
    var r=confirm("确定删除吗？三思奥！");
    if (r==true){
        let id = $(a).attr('id');
        $.ajax({
            url:"/api/v1/diary/delete/"+id,
            success(json){
                if (json.state==2000){
                    alert("删除成功！");
                    window.location="/";
                }else{
                    alert("服务器繁忙，删除失败，请稍后再试！")
                }
            }
        })
    }
    else{

    }
}