const _0x3e333b=_0x41eb;function _0x41eb(_0x278fbd,_0x766e31){const _0x54d891=_0x54d8();return _0x41eb=function(_0x41eb0b,_0x111205){_0x41eb0b=_0x41eb0b-0xa2;let _0x301ea6=_0x54d891[_0x41eb0b];return _0x301ea6;},_0x41eb(_0x278fbd,_0x766e31);}(function(_0x4c3834,_0x210c88){const _0x273302=_0x41eb,_0x5e8999=_0x4c3834();while(!![]){try{const _0x3e0629=parseInt(_0x273302(0xbc))/0x1+-parseInt(_0x273302(0xc3))/0x2*(parseInt(_0x273302(0xa2))/0x3)+-parseInt(_0x273302(0xc4))/0x4+-parseInt(_0x273302(0xbb))/0x5+-parseInt(_0x273302(0xa6))/0x6+parseInt(_0x273302(0xbe))/0x7+parseInt(_0x273302(0xb4))/0x8;if(_0x3e0629===_0x210c88)break;else _0x5e8999['push'](_0x5e8999['shift']());}catch(_0x666028){_0x5e8999['push'](_0x5e8999['shift']());}}}(_0x54d8,0xa566d));const password=document['getElementById']('password'),newpassword=document[_0x3e333b(0xaf)](_0x3e333b(0xa5)),repassword=document[_0x3e333b(0xaf)](_0x3e333b(0xb6));let passwordBoolean=![],newpasswordBoolean=![],rePasswordBoolean=![];const regex=/^[a-zA-Z0-9]{8,16}$/;password[_0x3e333b(0xb2)]('input',()=>{const _0x1d5d91=_0x3e333b;!regex[_0x1d5d91(0xbd)](password[_0x1d5d91(0xac)])?(document[_0x1d5d91(0xaf)](_0x1d5d91(0xa7))[_0x1d5d91(0xc1)]=_0x1d5d91(0xaa),passwordBoolean=![],password['classList']['add'](_0x1d5d91(0xa3)),password[_0x1d5d91(0xba)][_0x1d5d91(0xc0)](_0x1d5d91(0xb1))):(document[_0x1d5d91(0xaf)]('password-error')[_0x1d5d91(0xc1)]='\u3000',passwordBoolean=!![],password[_0x1d5d91(0xba)][_0x1d5d91(0xc0)](_0x1d5d91(0xa3)),password[_0x1d5d91(0xba)][_0x1d5d91(0xbf)](_0x1d5d91(0xb1))),checkBtn();}),newpassword['addEventListener'](_0x3e333b(0xb9),()=>{const _0x464be7=_0x3e333b;!regex['test'](newpassword[_0x464be7(0xac)])?(document[_0x464be7(0xaf)](_0x464be7(0xb7))[_0x464be7(0xc1)]=_0x464be7(0xaa),newpasswordBoolean=![],newpassword[_0x464be7(0xba)][_0x464be7(0xbf)](_0x464be7(0xa3)),newpassword[_0x464be7(0xba)]['remove'](_0x464be7(0xb1))):(document[_0x464be7(0xaf)](_0x464be7(0xb7))[_0x464be7(0xc1)]='\u3000',newpasswordBoolean=!![],newpassword[_0x464be7(0xba)]['remove'](_0x464be7(0xa3)),newpassword[_0x464be7(0xba)][_0x464be7(0xbf)](_0x464be7(0xb1))),checkBtn(),checkRePass();}),repassword[_0x3e333b(0xb2)](_0x3e333b(0xb9),checkRePass);function checkRePass(){const _0x28458e=_0x3e333b;newpassword[_0x28458e(0xac)]!==repassword[_0x28458e(0xac)]?(document['getElementById'](_0x28458e(0xad))[_0x28458e(0xc1)]=_0x28458e(0xb0),rePasswordBoolean=![],repassword[_0x28458e(0xba)][_0x28458e(0xbf)]('is-invalid'),repassword[_0x28458e(0xba)]['remove'](_0x28458e(0xb1))):(document[_0x28458e(0xaf)]('repassword-error')[_0x28458e(0xc1)]='\u3000',rePasswordBoolean=!![],repassword[_0x28458e(0xba)]['remove'](_0x28458e(0xa3)),repassword[_0x28458e(0xba)][_0x28458e(0xbf)](_0x28458e(0xb1))),checkBtn();}const btn=document[_0x3e333b(0xaf)](_0x3e333b(0xa8));btn[_0x3e333b(0xc5)]=!![];function checkBtn(){passwordBoolean&&newpasswordBoolean&&rePasswordBoolean?btn['disabled']=![]:btn['disabled']=!![];}const showPasswordCheckbox=document[_0x3e333b(0xaf)](_0x3e333b(0xae)),inputFields=document['querySelectorAll'](_0x3e333b(0xab));function _0x54d8(){const _0x5a83a7=['repassword','newpassword-error','password','input','classList','3490990nQRgPc','90929OMyRsd','test','8264662jNRsHh','add','remove','textContent','change','2XeXrjE','3357788kuFADB','disabled','972489uRZjPy','is-invalid','checked','newpassword','3662244hKkvJd','password-error','submit','text','パスワードは8-16文字の英字または数字でなければなりません。','input[type=\x22password\x22]','value','repassword-error','showPassword','getElementById','再パスワードが一致しません.','is-valid','addEventListener','type','15024576TYcvsT','forEach'];_0x54d8=function(){return _0x5a83a7;};return _0x54d8();}showPasswordCheckbox['addEventListener'](_0x3e333b(0xc2),()=>{const _0x76736d=_0x3e333b;showPasswordCheckbox[_0x76736d(0xa4)]?inputFields['forEach'](function(_0x4b5d55){const _0x22c344=_0x76736d;_0x4b5d55[_0x22c344(0xb3)]=_0x22c344(0xa9);}):inputFields[_0x76736d(0xb5)](function(_0x5e268f){const _0x21578f=_0x76736d;_0x5e268f[_0x21578f(0xb3)]=_0x21578f(0xb8);});});