const _0x3b7a5a=_0x1e43;(function(_0x3936e2,_0x40af69){const _0x97f3d0=_0x1e43,_0x3fe56f=_0x3936e2();while(!![]){try{const _0x522851=-parseInt(_0x97f3d0(0x182))/0x1+-parseInt(_0x97f3d0(0x189))/0x2+parseInt(_0x97f3d0(0x181))/0x3+parseInt(_0x97f3d0(0x19d))/0x4*(parseInt(_0x97f3d0(0x187))/0x5)+-parseInt(_0x97f3d0(0x194))/0x6*(parseInt(_0x97f3d0(0x17f))/0x7)+-parseInt(_0x97f3d0(0x18b))/0x8+parseInt(_0x97f3d0(0x188))/0x9*(parseInt(_0x97f3d0(0x197))/0xa);if(_0x522851===_0x40af69)break;else _0x3fe56f['push'](_0x3fe56f['shift']());}catch(_0x492ce4){_0x3fe56f['push'](_0x3fe56f['shift']());}}}(_0x3e80,0xa67c9));const emailInput=document['querySelector'](_0x3b7a5a(0x18d)),emailError=document['getElementById'](_0x3b7a5a(0x195)),btn=document[_0x3b7a5a(0x18c)](_0x3b7a5a(0x18f));let checkForm=!![];emailInput['value']=localStorage['getItem'](_0x3b7a5a(0x1a3));const linkSend=document[_0x3b7a5a(0x199)](_0x3b7a5a(0x192));function _0x1e43(_0xa59a19,_0x35eac9){const _0x3e804d=_0x3e80();return _0x1e43=function(_0x1e433d,_0x4f1098){_0x1e433d=_0x1e433d-0x17e;let _0x24b9ed=_0x3e804d[_0x1e433d];return _0x24b9ed;},_0x1e43(_0xa59a19,_0x35eac9);}linkSend[_0x3b7a5a(0x191)]=_0x3b7a5a(0x185)+encodeURIComponent(localStorage[_0x3b7a5a(0x18e)](_0x3b7a5a(0x1a3)));let checkemail=!![];const tokenInput=document[_0x3b7a5a(0x199)](_0x3b7a5a(0x19b)),tokenError=document[_0x3b7a5a(0x18c)](_0x3b7a5a(0x1a0));let checktoken=!![];tokenInput[_0x3b7a5a(0x186)](_0x3b7a5a(0x180),function(){const _0xb951d3=_0x3b7a5a;let _0x3393b7=tokenInput['value'];const _0x281868=/^[0-9]{6}$/;_0x281868[_0xb951d3(0x190)](_0x3393b7)?(tokenError[_0xb951d3(0x193)]='\u3000',checktoken=![],tokenInput[_0xb951d3(0x1a1)]['remove'](_0xb951d3(0x184)),tokenInput[_0xb951d3(0x1a1)][_0xb951d3(0x19c)](_0xb951d3(0x19f))):(checktoken=!![],tokenError[_0xb951d3(0x193)]=_0xb951d3(0x19e),tokenInput[_0xb951d3(0x1a1)][_0xb951d3(0x196)](_0xb951d3(0x19f)),tokenInput[_0xb951d3(0x1a1)][_0xb951d3(0x19c)]('is-invalid')),checkInput();});const passwordInput=document['querySelector'](_0x3b7a5a(0x183)),passwordError=document[_0x3b7a5a(0x18c)](_0x3b7a5a(0x19a));let checkpassword=!![];passwordInput['addEventListener'](_0x3b7a5a(0x180),function(){const _0x2d33ba=_0x3b7a5a;let _0x5a129c=passwordInput[_0x2d33ba(0x198)];const _0x3c62ca=/^[a-zA-Z0-9]{8,18}$/;_0x3c62ca[_0x2d33ba(0x190)](_0x5a129c)?(passwordError[_0x2d33ba(0x193)]='\u3000',checkpassword=![],passwordInput['classList'][_0x2d33ba(0x196)](_0x2d33ba(0x184)),passwordInput[_0x2d33ba(0x1a1)][_0x2d33ba(0x19c)](_0x2d33ba(0x19f))):(checkpassword=!![],passwordError[_0x2d33ba(0x193)]=_0x2d33ba(0x1a2),passwordInput[_0x2d33ba(0x1a1)][_0x2d33ba(0x196)](_0x2d33ba(0x19f)),passwordInput[_0x2d33ba(0x1a1)][_0x2d33ba(0x19c)](_0x2d33ba(0x184))),checkInput();});function _0x3e80(){const _0x2719ad=['2492126FKBzqN','rePassword-error','1289840cTnoTS','getElementById','#email','getItem','submit','test','href','#linkSend','textContent','14034IKnKRU','email-error','remove','130310bbTzsI','value','querySelector','password-error','#token','add','323668DkKLzB','トークンは6文字の数字のみです。','is-valid','token-error','classList','パスワードは8-18文字の英文字または数字のみです。','sendEmail','disabled','2779RMumto','input','3892635MoAljy','732299RwKlEg','#password','is-invalid','/sendtoken?email=','addEventListener','5TisKTq','1638MjXAEI'];_0x3e80=function(){return _0x2719ad;};return _0x3e80();}const rePasswordInput=document['querySelector']('#rePassword'),rePasswordError=document['getElementById'](_0x3b7a5a(0x18a));let checkrePassword=!![];rePasswordInput[_0x3b7a5a(0x186)](_0x3b7a5a(0x180),function(){const _0x4c3bda=_0x3b7a5a;passwordInput[_0x4c3bda(0x198)]===rePasswordInput['value']?(rePasswordError[_0x4c3bda(0x193)]='\u3000',checkrePassword=![],rePasswordInput['classList'][_0x4c3bda(0x196)](_0x4c3bda(0x184)),rePasswordInput[_0x4c3bda(0x1a1)][_0x4c3bda(0x19c)](_0x4c3bda(0x19f))):(checkrePassword=!![],rePasswordError['textContent']='パスワード再入力が一致しません。',rePasswordInput['classList']['remove']('is-valid'),rePasswordInput[_0x4c3bda(0x1a1)][_0x4c3bda(0x19c)](_0x4c3bda(0x184))),checkInput();});function checkInput(){const _0x557d88=_0x3b7a5a;checktoken||checkpassword||checkrePassword?btn[_0x557d88(0x17e)]=!![]:btn[_0x557d88(0x17e)]=![];}checkInput();