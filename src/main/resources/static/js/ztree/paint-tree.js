  var zTreeObj;
   // zTree configuration information, refer to API documentation (setting details)
   var setting = {
    check: {
      autoCheckTrigger:false,
      chkStyle: "checkbox",
      enable:true,
      chkboxType:{"Y":"ps","N":"ps"},
      nocheckInherit:false,
      chkDisabledInherit:false,
      radioType:"level"
    }

   };
   // zTree data attributes, refer to the API documentation (treeNode data details)
   var zNodes = [
   {name:"페인트 오피스", open:true, children:[
      {name:"인사팀"}, {name:"재무회계팀"},
      {name:"개발팀"}, {name:"생산팀"},{name:"영업팀"}]},
   {name:"test", open:true, children:[
      {name:"test_1"}, {name:"test_2"}]}
   ];
   $(document).ready(function(){
      zTreeObj = $.fn.zTree.init($("#tree"), setting, zNodes);
   });