"use strict";var PAGE_SIZE=20,selectData=[],deleteData=function(e){WUI.ModalDialog.create({message:"11111",title:"2222",onConfirm:function(){console.log("delete data",e)}})},change=function(e){var a=WUI.ModalDialog.create({width:"40%",title:"脱敏规则定义"});WUI.ajax({url:"/warehouse/maskRules",method:"GET"}).done(function(t){var i="";""!=selectData&&selectData.map(function(a){a.colName==e.name&&(i=a)}),WUI.SensitiveDialog.create({selectData:t.data,$el:$(".modal-body"),selectList:""!=i&&i,onConfirm:function(t){if(""==t.maskRuleID)$("#error-message").html("请设置脱敏规则");else if(""==t.maskParmas)$("#error-message").html("请设置脱敏参数");else{a.hide();var i={};i.colName=e.name,i.rule=t,selectData.push(i),$("#"+e.name).text("修改规则"),$("#sensitive-submit").removeClass("sensitive-default"),$("#sensitive-submit").addClass("sensitive-button"),$("#sensitive-submit").removeAttr("disabled")}}})})};WUI.ready=function(){var e=WUI.link().source,a=WUI.link().setMenu2,t=WUI.link().setMenu3;WUI.ContentHeader.create({$el:$(".content-header"),meta:[{name:"列表页面"}]});var i={source:e,datasetType:"TABLE",schema:a,name:t,rows:"20"};WUI.ajax({url:"/data/table",method:"GET",data:i}).done(function(e){WUI.ChangeTables.create({$el:$(".data-table"),titles:e.data.titles,list:e.data.data,onclick:function(e){change(e)}})}),$("#sensitive-submit").click(function(){var e=WUI.ModalDialog.create({width:"30%",title:"开始脱敏",onConfirm:function(){var a=$("#submit-type").val(),t=$("#submit-name").val();if(""==a)$("#submit-error").html("请选择名称类型");else if(""==t)$("#submit-error").html("请输入表名称");else{e.hide()}}});$(".modal-body").append('<div class="row"><div class="col-md-12 form-group"><select id="submit-type" class="form-control"> <option value="" >请设置名称类型(表或视图)</option><option value="table" >表</option><option value="view" >视图</option></select></div><div class="col-md-12 form-group"><input id="submit-name" style="margin-bottom: 10px" type="text" class="form-control" placeholder="请输出名称"></div></div><div class="row"><div class="col-md-12 form-group" id="sensitive-input"><span style="color: red; font-size: 15px;" id="submit-error"></span></div></div>')}),$("#give-user").click(function(){var i="",s=WUI.ModalDialog.create({width:"50%",title:"授权用户",onConfirm:function(){if(i){var o={source:e,datasetType:"TABLE",schema:a,name:t,user:i};WUI.ajax({url:"/admin/grant",method:"POST",data:o}).done(function(e){s.hide()})}else $("#user-error").html("请输入授权用户")}});$(".modal-body").append('<div class="row"><div class="col-md-12 form-group"><div id="suggest"></div></div><div class="col-md-12 form-group" id="sensitive-input"><span style="color: red; font-size: 15px;" id="user-error"></span></div></div>'),WUI.ajax({url:"/admin/users",method:"GET"}).done(function(e){var a=[];e.data.map(function(e){a.push(e.name)});var t=$("#suggest").magicSuggest({placeholder:"请选择授权用户",data:a});$(t).on("selectionchange",function(e,a,t){i=a.getValue()})})}),$("#check-risk").click(function(){var i={source:e,datasetType:"TABLE",schema:a,name:t,columns:"20"};WUI.ajax({url:"/warehouse/privacyRisk",method:"GET",data:i}).done(function(e){$(".data-filter").empty();var a=e.data;WUI.riskTable.create({$el:$(".data-filter"),list:a})})}),$("#delete-table").click(function(){var i=WUI.ModalDialog.create({width:"30%",title:"删除视图/表",message:t,onConfirm:function(){var s={source:e,datasetType:"TABLE",schema:a,name:t};WUI.ajax({url:"/warehouse/dataset/delete",method:"POST",data:s}).done(function(e){i.hide()})}})})},$(function(){WUI.init({system:"sensitive"})});
//# sourceMappingURL=sensitive-detail.js.map
