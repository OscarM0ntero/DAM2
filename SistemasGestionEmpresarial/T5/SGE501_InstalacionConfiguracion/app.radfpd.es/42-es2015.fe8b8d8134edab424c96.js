(window.webpackJsonp=window.webpackJsonp||[]).push([[42],{"0G8V":function(t,i,o){"use strict";o.r(i),o.d(i,"UsuariosModule",(function(){return K}));var e=o("ofXK"),a=o("tyNb"),r=o("mrSG"),s=o("M9IT"),n=o("Dh3D"),c=o("+0xr"),l=o("3Pt+"),u=o("Xksa"),b=o("fXoL"),d=o("0IaG"),m=o("0ceX"),f=o("on2l"),h=o("dNgK"),p=o("kmnG"),v=o("qFsG"),g=o("d3UM"),S=o("bTqV"),C=o("FKr1");function R(t,i){if(1&t&&(b.Sb(0,"mat-option",14),b.Cc(1),b.Rb()),2&t){const t=i.$implicit;b.jc("value",t.id_rol),b.Bb(1),b.Ec(" ",t.rol," ")}}let w=(()=>{class t{constructor(t,i,o,e){this.dialogRef=t,this.servicioRoles=i,this.servicioUsuario=o,this.snackBar=e}ngOnInit(){this.usuarioForm=new l.h({usuario:new l.e(null,[l.v.required,l.v.email]),password:new l.e(null,[l.v.required]),id_rol:new l.e(null,[l.v.required]),nombre_publico:new l.e(null),observaciones:new l.e(null)}),this.getRoles()}getRoles(){return Object(r.a)(this,void 0,void 0,(function*(){const t=yield this.servicioRoles.getAllRoles().toPromise();t.ok&&(this.roles=t.data)}))}confirmAdd(){return Object(r.a)(this,void 0,void 0,(function*(){if(this.usuarioForm.valid){const t=this.usuarioForm.value,i=yield this.servicioUsuario.addUsuario(t).toPromise();i.ok?(this.snackBar.open(i.message,u.a,{duration:5e3}),this.dialogRef.close({ok:i.ok,data:i.data})):this.snackBar.open(i.message,u.a,{duration:5e3})}else this.snackBar.open(u.n,u.a,{duration:5e3})}))}onNoClick(){this.dialogRef.close({ok:!1})}}return t.\u0275fac=function(i){return new(i||t)(b.Mb(d.f),b.Mb(m.a),b.Mb(f.a),b.Mb(h.a))},t.\u0275cmp=b.Gb({type:t,selectors:[["app-add-usuario"]],decls:27,vars:3,consts:[[1,"container"],["mat-dialog-title",""],[1,"mat-dialog-content",3,"formGroup"],[1,"form"],["color","accent"],["matInput","","placeholder","Correo electr\xf3nico","formControlName","usuario","name","usuario","required",""],["matInput","","placeholder","Nombre P\xfablico","formControlName","nombre_publico","name","nombre_publico"],["matInput","","type","password","placeholder","Contrase\xf1a","formControlName","password","name","password","required",""],["formControlName","id_rol"],[3,"value",4,"ngFor","ngForOf"],["matInput","","placeholder","Observaciones","formControlName","observaciones","name","observaciones"],["mat-dialog-actions",""],["id","onSubmit","mat-button","","type","submit",3,"disabled","click"],["id","onNoClick","mat-button","","tabindex","-1",3,"click"],[3,"value"]],template:function(t,i){1&t&&(b.Sb(0,"div",0),b.Sb(1,"h3",1),b.Cc(2,"A\xf1adir usuario"),b.Rb(),b.Sb(3,"form",2),b.Sb(4,"div",3),b.Sb(5,"mat-form-field",4),b.Nb(6,"input",5),b.Rb(),b.Rb(),b.Sb(7,"div",3),b.Sb(8,"mat-form-field",4),b.Nb(9,"input",6),b.Rb(),b.Rb(),b.Sb(10,"div",3),b.Sb(11,"mat-form-field",4),b.Nb(12,"input",7),b.Rb(),b.Rb(),b.Sb(13,"div",3),b.Sb(14,"mat-form-field"),b.Sb(15,"mat-label"),b.Cc(16,"Rol"),b.Rb(),b.Sb(17,"mat-select",8),b.Bc(18,R,2,2,"mat-option",9),b.Rb(),b.Rb(),b.Rb(),b.Sb(19,"div",3),b.Sb(20,"mat-form-field",4),b.Nb(21,"textarea",10),b.Rb(),b.Rb(),b.Sb(22,"div",11),b.Sb(23,"button",12),b.Zb("click",(function(){return i.confirmAdd()})),b.Cc(24,"Guardar"),b.Rb(),b.Sb(25,"button",13),b.Zb("click",(function(){return i.onNoClick()})),b.Cc(26,"Cancelar"),b.Rb(),b.Rb(),b.Rb(),b.Rb()),2&t&&(b.Bb(3),b.jc("formGroup",i.usuarioForm),b.Bb(15),b.jc("ngForOf",i.roles),b.Bb(5),b.jc("disabled",!i.usuarioForm.valid))},directives:[d.g,l.w,l.p,l.i,p.c,v.b,l.c,l.o,l.g,l.u,p.f,g.a,e.k,d.c,S.a,C.n],styles:[".mat-dialog-content[_ngcontent-%COMP%]{overflow:hidden;width:18em}.container[_ngcontent-%COMP%]{margin:0 auto;width:18em;text-align:center;padding-top:10px}div.mat-dialog-actions[_ngcontent-%COMP%]{width:12em;margin:auto}"]}),t})();var k=o("bSwM");function B(t,i){if(1&t&&(b.Sb(0,"mat-option",16),b.Cc(1),b.Rb()),2&t){const t=i.$implicit;b.jc("value",t.id_rol),b.Bb(1),b.Ec(" ",t.rol," ")}}let N=(()=>{class t{constructor(t,i,o,e,a){this.dialogRef=t,this.servicioRoles=i,this.servicioUsuario=o,this.snackBar=e,this.usuario=a}ngOnInit(){this.usuarioForm=new l.h({id_usuario:new l.e(this.usuario.id_usuario,[l.v.required]),usuario:new l.e(this.usuario.usuario,[l.v.required,l.v.email]),nombre_publico:new l.e(this.usuario.nombre_publico),password:new l.e(""),habilitado:new l.e(1===Number(this.usuario.habilitado),[l.v.required]),id_rol:new l.e(this.usuario.id_rol,[l.v.required]),observaciones:new l.e(this.usuario.observaciones)}),this.getRoles()}getRoles(){return Object(r.a)(this,void 0,void 0,(function*(){const t=yield this.servicioRoles.getAllRoles().toPromise();t.ok&&(this.roles=t.data)}))}confirmAdd(){return Object(r.a)(this,void 0,void 0,(function*(){if(this.usuarioForm.valid){const t=this.usuarioForm.value,i=yield this.servicioUsuario.editUsuario(t).toPromise();i.ok?(this.snackBar.open(i.message,u.a,{duration:5e3}),this.dialogRef.close({ok:i.ok,data:i.data})):this.snackBar.open(i.message,u.a,{duration:5e3})}else this.snackBar.open(u.n,u.a,{duration:5e3})}))}onNoClick(){this.dialogRef.close({ok:!1})}}return t.\u0275fac=function(i){return new(i||t)(b.Mb(d.f),b.Mb(m.a),b.Mb(f.a),b.Mb(h.a),b.Mb(d.a))},t.\u0275cmp=b.Gb({type:t,selectors:[["app-edit-usuario"]],decls:30,vars:3,consts:[[1,"container"],["mat-dialog-title",""],[1,"mat-dialog-content",3,"formGroup"],[1,"form"],["color","accent"],["matInput","","placeholder","Correo electr\xf3nico","formControlName","usuario","name","usuario","required",""],["matInput","","placeholder","Nombre p\xfablico","formControlName","nombre_publico","name","nombre_publico","required",""],["matInput","","type","password","placeholder","Cambiar contrase\xf1a","formControlName","password","name","password"],["formControlName","id_rol"],[3,"value",4,"ngFor","ngForOf"],[1,"form","habilitado"],["formControlName","habilitado"],["matInput","","placeholder","Observaciones","formControlName","observaciones","name","observaciones"],["mat-dialog-actions",""],["mat-button","","id","onSubmit","type","submit",3,"disabled","click"],["mat-button","","id","onNoClick","tabindex","-1",3,"click"],[3,"value"]],template:function(t,i){1&t&&(b.Sb(0,"div",0),b.Sb(1,"h3",1),b.Cc(2,"Editar usuario"),b.Rb(),b.Sb(3,"form",2),b.Sb(4,"div",3),b.Sb(5,"mat-form-field",4),b.Nb(6,"input",5),b.Rb(),b.Rb(),b.Sb(7,"div",3),b.Sb(8,"mat-form-field",4),b.Nb(9,"input",6),b.Rb(),b.Rb(),b.Sb(10,"div",3),b.Sb(11,"mat-form-field",4),b.Nb(12,"input",7),b.Rb(),b.Rb(),b.Sb(13,"div",3),b.Sb(14,"mat-form-field"),b.Sb(15,"mat-label"),b.Cc(16,"Rol"),b.Rb(),b.Sb(17,"mat-select",8),b.Bc(18,B,2,2,"mat-option",9),b.Rb(),b.Rb(),b.Rb(),b.Sb(19,"div",10),b.Sb(20,"mat-checkbox",11),b.Cc(21,"Usuario habilitado"),b.Rb(),b.Rb(),b.Sb(22,"div",3),b.Sb(23,"mat-form-field",4),b.Nb(24,"textarea",12),b.Rb(),b.Rb(),b.Sb(25,"div",13),b.Sb(26,"button",14),b.Zb("click",(function(){return i.confirmAdd()})),b.Cc(27,"Guardar"),b.Rb(),b.Sb(28,"button",15),b.Zb("click",(function(){return i.onNoClick()})),b.Cc(29,"Cancelar"),b.Rb(),b.Rb(),b.Rb(),b.Rb()),2&t&&(b.Bb(3),b.jc("formGroup",i.usuarioForm),b.Bb(15),b.jc("ngForOf",i.roles),b.Bb(8),b.jc("disabled",!i.usuarioForm.valid))},directives:[d.g,l.w,l.p,l.i,p.c,v.b,l.c,l.o,l.g,l.u,p.f,g.a,e.k,k.a,d.c,S.a,C.n],styles:[".mat-dialog-content[_ngcontent-%COMP%]{overflow:hidden;width:18em}.container[_ngcontent-%COMP%]{margin:0 auto;width:18em;text-align:center;padding-top:10px}div.mat-dialog-actions[_ngcontent-%COMP%]{width:12em;margin:auto}div#contain[_ngcontent-%COMP%]{text-align:justify}.form.habilitado[_ngcontent-%COMP%]{text-align:left!important}"]}),t})(),y=(()=>{class t{constructor(t,i,o,e){this.dialogRef=t,this.usuario=i,this.servicioUsuario=o,this.snackBar=e}ngOnInit(){}deleteUser(){return Object(r.a)(this,void 0,void 0,(function*(){const t=yield this.servicioUsuario.deleteUsuario(this.usuario).toPromise();t.ok?(this.snackBar.open(t.message,u.a,{duration:5e3}),this.dialogRef.close({ok:t.ok,data:t.data})):this.snackBar.open(t.message,u.a,{duration:5e3})}))}onNoClick(){this.dialogRef.close({ok:!1})}}return t.\u0275fac=function(i){return new(i||t)(b.Mb(d.f),b.Mb(d.a),b.Mb(f.a),b.Mb(h.a))},t.\u0275cmp=b.Gb({type:t,selectors:[["app-delete-usuario"]],decls:14,vars:1,consts:[[1,"container"],["mat-dialog-title",""],["mat-dialog-actions",""],["id","deleteUser","mat-button","","type","submit",3,"click"],["id","onNoClick","mat-button","","tabindex","-1",3,"click"]],template:function(t,i){1&t&&(b.Sb(0,"div",0),b.Sb(1,"h3",1),b.Cc(2,"Borrar usuario"),b.Rb(),b.Sb(3,"p"),b.Sb(4,"strong"),b.Cc(5,"\xa1Atenci\xf3n! Se eliminar\xe1 el usuario:"),b.Rb(),b.Cc(6),b.Rb(),b.Sb(7,"p"),b.Cc(8," Esto sirve para eliminar usuarios creados por error, en otro caso es recomendable deshabilitar el usuario desde el panel de editar."),b.Rb(),b.Sb(9,"div",2),b.Sb(10,"button",3),b.Zb("click",(function(){return i.deleteUser()})),b.Cc(11,"Eliminar"),b.Rb(),b.Sb(12,"button",4),b.Zb("click",(function(){return i.onNoClick()})),b.Cc(13,"Cancelar"),b.Rb(),b.Rb(),b.Rb()),2&t&&(b.Bb(6),b.Ec(" ",i.usuario.usuario," "))},directives:[d.g,d.c,S.a],styles:[""]}),t})();var U=o("rDax"),O=o("NFeN");function _(t,i){if(1&t&&(b.Sb(0,"th",15),b.Nb(1,"input",16),b.Nb(2,"span",17),b.Rb()),2&t){const t=b.dc();b.Bb(1),b.jc("formControl",t.idFilter)}}function F(t,i){if(1&t&&(b.Sb(0,"td",18),b.Cc(1),b.Rb()),2&t){const t=i.$implicit;b.Bb(1),b.Ec(" ",t.id_usuario," ")}}function j(t,i){if(1&t&&(b.Sb(0,"th",15),b.Nb(1,"input",19),b.Nb(2,"span",17),b.Rb()),2&t){const t=b.dc();b.Bb(1),b.jc("formControl",t.usuarioFilter)}}function x(t,i){if(1&t&&(b.Sb(0,"td",18),b.Cc(1),b.Rb()),2&t){const t=i.$implicit;b.Bb(1),b.Ec(" ",t.usuario," ")}}function M(t,i){if(1&t&&(b.Sb(0,"th",15),b.Nb(1,"input",20),b.Nb(2,"span",17),b.Rb()),2&t){const t=b.dc();b.Bb(1),b.jc("formControl",t.nombreFilter)}}function P(t,i){if(1&t&&(b.Sb(0,"td",18),b.Cc(1),b.Rb()),2&t){const t=i.$implicit;b.Bb(1),b.Ec(" ",t.nombre_publico," ")}}function I(t,i){if(1&t&&(b.Sb(0,"th",15),b.Nb(1,"input",21),b.Nb(2,"span",17),b.Rb()),2&t){const t=b.dc();b.Bb(1),b.jc("formControl",t.rolFilter)}}function D(t,i){if(1&t&&(b.Sb(0,"td",18),b.Cc(1),b.Rb()),2&t){const t=i.$implicit;b.Bb(1),b.Ec(" ",t.rol," ")}}function G(t,i){if(1&t){const t=b.Tb();b.Sb(0,"th",15),b.Sb(1,"mat-form-field",22),b.Sb(2,"mat-select",23),b.Zb("selectionChange",(function(i){return b.tc(t),b.dc().buscarHabilitados(i)})),b.Sb(3,"mat-option",24),b.Cc(4," Todos "),b.Rb(),b.Sb(5,"mat-option",25),b.Cc(6," Si "),b.Rb(),b.Sb(7,"mat-option",26),b.Cc(8," No "),b.Rb(),b.Rb(),b.Rb(),b.Rb()}}function q(t,i){if(1&t&&(b.Sb(0,"td",18),b.Cc(1),b.Rb()),2&t){const t=i.$implicit;b.Bb(1),b.Ec(" ","1"==t.habilitado?"S\xed":"No"," ")}}function V(t,i){if(1&t){const t=b.Tb();b.Sb(0,"button",28),b.Zb("click",(function(){return b.tc(t),b.dc(2).addUsuario()})),b.Sb(1,"mat-icon"),b.Cc(2," add_circle "),b.Rb(),b.Rb()}}function E(t,i){if(1&t&&(b.Sb(0,"th",15),b.Bc(1,V,3,0,"button",27),b.Rb()),2&t){const t=b.dc();b.Bb(1),b.jc("ngIf",t.permises.add)}}function J(t,i){if(1&t){const t=b.Tb();b.Sb(0,"button",31),b.Zb("click",(function(){b.tc(t);const i=b.dc().$implicit;return b.dc().editUsuario(i)})),b.Sb(1,"mat-icon"),b.Cc(2," mode_edit "),b.Rb(),b.Rb()}}function Z(t,i){if(1&t){const t=b.Tb();b.Sb(0,"button",32),b.Zb("click",(function(){b.tc(t);const i=b.dc().$implicit;return b.dc().deleteUsuario(i)})),b.Sb(1,"mat-icon"),b.Cc(2," delete "),b.Rb(),b.Rb()}}function A(t,i){if(1&t&&(b.Sb(0,"td",18),b.Bc(1,J,3,0,"button",29),b.Bc(2,Z,3,0,"button",30),b.Rb()),2&t){const t=b.dc();b.Bb(1),b.jc("ngIf",t.permises.edit),b.Bb(1),b.jc("ngIf",t.permises.delete)}}function $(t,i){1&t&&b.Nb(0,"tr",33)}function L(t,i){1&t&&b.Nb(0,"tr",34)}const T=function(){return[5,10,25]},Q=[{path:"",component:(()=>{class t{constructor(t,i,o){this.dialog=t,this.servicioUsuarios=i,this.overlay=o,this.dataSource=new c.k,this.idFilter=new l.e,this.usuarioFilter=new l.e,this.nombreFilter=new l.e,this.rolFilter=new l.e,this.displayTable=!1,this.filterValues={id_usuario:"",usuario:"",nombre_publico:"",rol:"",habilitado:0}}ngOnInit(){this.getUsuarios()}getUsuarios(){return Object(r.a)(this,void 0,void 0,(function*(){const t=yield this.servicioUsuarios.getAllUsuarios().toPromise();this.permises=t.permises,t.ok&&(this.displayedColumns=["id_usuario","usuario","nombre_publico","rol","habilitado","actions"],this.servicioUsuarios.usuarios=t.data,this.dataSource.data=this.servicioUsuarios.usuarios,this.dataSource.sort=this.sort,this.dataSource.paginator=this.paginator,this.dataSource.filterPredicate=this.createFilter(),this.onChanges())}))}addUsuario(){return Object(r.a)(this,void 0,void 0,(function*(){const t=this.dialog.open(w,{width:"500px",scrollStrategy:this.overlay.scrollStrategies.noop()}),i=yield t.afterClosed().toPromise();i&&i.ok&&(this.servicioUsuarios.usuarios.push(i.data),this.dataSource.data=this.servicioUsuarios.usuarios)}))}editUsuario(t){return Object(r.a)(this,void 0,void 0,(function*(){const i=this.dialog.open(N,{data:t,width:"500px",scrollStrategy:this.overlay.scrollStrategies.noop()}),o=yield i.afterClosed().toPromise();o&&o.ok&&(this.servicioUsuarios.updateUsuario(o.data),this.dataSource.data=this.servicioUsuarios.usuarios)}))}deleteUsuario(t){return Object(r.a)(this,void 0,void 0,(function*(){const i=this.dialog.open(y,{data:t,scrollStrategy:this.overlay.scrollStrategies.noop()}),o=yield i.afterClosed().toPromise();o&&o.ok&&(this.servicioUsuarios.removeUsuario(o.data),this.dataSource.data=this.servicioUsuarios.usuarios)}))}createFilter(){return(t,i)=>{const o=JSON.parse(i);return-1!==t.id_usuario.toString().indexOf(o.id_usuario.toLowerCase())&&-1!==t.usuario.toLowerCase().indexOf(o.usuario.toLowerCase())&&-1!==t.nombre_publico.toLowerCase().indexOf(o.nombre_publico.toLowerCase())&&-1!==t.rol.toLowerCase().indexOf(o.rol.toLowerCase())&&"todos"===o.habilitado?t.habilitado:-1!==t.habilitado.indexOf(o.habilitado)}}onChanges(){this.idFilter.valueChanges.subscribe(t=>{this.filterValues.id_usuario=t,this.dataSource.filter=JSON.stringify(this.filterValues)}),this.usuarioFilter.valueChanges.subscribe(t=>{this.filterValues.usuario=t,this.dataSource.filter=JSON.stringify(this.filterValues)}),this.nombreFilter.valueChanges.subscribe(t=>{this.filterValues.nombre_publico=t,this.dataSource.filter=JSON.stringify(this.filterValues)}),this.rolFilter.valueChanges.subscribe(t=>{this.filterValues.rol=t,this.dataSource.filter=JSON.stringify(this.filterValues)})}buscarHabilitados(t){let i;i="todos"===t.value?t.value:Number(t.value),this.filterValues.habilitado=i,this.dataSource.filter=JSON.stringify(this.filterValues)}}return t.\u0275fac=function(i){return new(i||t)(b.Mb(d.b),b.Mb(f.a),b.Mb(U.c))},t.\u0275cmp=b.Gb({type:t,selectors:[["app-usuarios"]],viewQuery:function(t,i){var o;1&t&&(b.xc(s.a,!0),b.xc(n.a,!0)),2&t&&(b.pc(o=b.ac())&&(i.paginator=o.first),b.pc(o=b.ac())&&(i.sort=o.first))},decls:25,vars:6,consts:[[1,"example-container","mat-elevation-z8"],[1,"example-table-container"],["mat-table","","id","table","matSort","",1,"mat-elevation-z8",3,"dataSource"],["matColumnDef","id_usuario"],["mat-header-cell","",4,"matHeaderCellDef"],["mat-cell","",4,"matCellDef"],["matColumnDef","usuario"],["matColumnDef","nombre_publico"],["matColumnDef","rol"],["matColumnDef","habilitado"],["matColumnDef","actions"],["mat-header-row","",4,"matHeaderRowDef"],["mat-row","",4,"matRowDef","matRowDefColumns"],[3,"pageSize","pageSizeOptions"],["paginator",""],["mat-header-cell",""],["matInput","","placeholder","Id",3,"formControl"],["mat-sort-header",""],["mat-cell",""],["matInput","","placeholder","Usuario",3,"formControl"],["matInput","","placeholder","Nombre",3,"formControl"],["matInput","","placeholder","Rol",3,"formControl"],["appearance","outline"],["placeholder","Habilitado",3,"selectionChange"],["value","todos"],["value","1"],["value","0"],["id","addUsuario","mat-icon-button","",3,"click",4,"ngIf"],["id","addUsuario","mat-icon-button","",3,"click"],["id","editUsuario","mat-icon-button","",3,"click",4,"ngIf"],["id","deleteUsuario","mat-icon-button","",3,"click",4,"ngIf"],["id","editUsuario","mat-icon-button","",3,"click"],["id","deleteUsuario","mat-icon-button","",3,"click"],["mat-header-row",""],["mat-row",""]],template:function(t,i){1&t&&(b.Sb(0,"div",0),b.Sb(1,"div",1),b.Sb(2,"table",2),b.Qb(3,3),b.Bc(4,_,3,1,"th",4),b.Bc(5,F,2,1,"td",5),b.Pb(),b.Qb(6,6),b.Bc(7,j,3,1,"th",4),b.Bc(8,x,2,1,"td",5),b.Pb(),b.Qb(9,7),b.Bc(10,M,3,1,"th",4),b.Bc(11,P,2,1,"td",5),b.Pb(),b.Qb(12,8),b.Bc(13,I,3,1,"th",4),b.Bc(14,D,2,1,"td",5),b.Pb(),b.Qb(15,9),b.Bc(16,G,9,0,"th",4),b.Bc(17,q,2,1,"td",5),b.Pb(),b.Qb(18,10),b.Bc(19,E,2,1,"th",4),b.Bc(20,A,3,2,"td",5),b.Pb(),b.Bc(21,$,1,0,"tr",11),b.Bc(22,L,1,0,"tr",12),b.Rb(),b.Rb(),b.Nb(23,"mat-paginator",13,14),b.Rb()),2&t&&(b.Bb(2),b.jc("dataSource",i.dataSource),b.Bb(19),b.jc("matHeaderRowDef",i.displayedColumns),b.Bb(1),b.jc("matRowDefColumns",i.displayedColumns),b.Bb(1),b.jc("pageSize",10)("pageSizeOptions",b.mc(5,T)))},directives:[c.j,n.a,c.c,c.e,c.b,c.g,c.i,s.a,c.d,v.b,l.c,l.o,l.f,n.b,c.a,p.c,g.a,C.n,e.l,S.a,O.a,c.f,c.h],styles:["mat-form-field[_ngcontent-%COMP%]{margin-top:20px}  .mat-form-field-infix{padding:5px!important;height:15px}"]}),t})()}];let z=(()=>{class t{}return t.\u0275mod=b.Kb({type:t}),t.\u0275inj=b.Jb({factory:function(i){return new(i||t)},imports:[[a.f.forChild(Q)],a.f]}),t})();var H=o("ah4G");let K=(()=>{class t{}return t.\u0275mod=b.Kb({type:t}),t.\u0275inj=b.Jb({factory:function(i){return new(i||t)},imports:[[e.c,z,H.a]]}),t})()}}]);