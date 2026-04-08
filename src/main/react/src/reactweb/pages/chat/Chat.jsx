import axios from "axios";
import { useEffect, useRef, useState } from "react"

// 6️⃣ npm i @stomp/stompjs 설치
// 7️⃣ stomp 클라이언트 객체 import
import { Client } from "@stomp/stompjs";

export default function Chat( props ){

    // 4️⃣ 로그인 정보를 담는 상태변수
    const [ loginUser , setLoginUser ] = useState( null );

    // 3️⃣ AXIOS 회원정보 불러오기 함수
    const getMyInfo = async() => {
        try{
            const response = await axios.get(
                "http://localhost:8080/api/member3/my/info",
                { withCredentials: true }
            )
            setLoginUser( response.data || null );
                // 단축평가 : 조건 && 참 (참일 때만 실행) , 조건 || 거짓 (거짓일 때만 실행)

            console.log(response.data)// 확인용

        }catch(e){console.log(e)}
    }

    // 8️⃣ Client 객체를 저장하는 (레퍼런스)변수 ( useRef(렌더링영향x) vs. useState(렌더링영향o) )
        // 특정한 상태/값 저장하고 화면 렌더링에 영향을 주지 않는 저장소 (= useRef )
        // useRef가 변해도 렌더링은 안 된다. useState는 변하면 렌더링된다.
    // useRef = { current : null } , useRef 객체 내 current 속성을 갖는다.
    // current는 저장된 상태/값
    // 만약 다른 상태들이 변해서 렌더링되었을 때 -> 채팅 클라이언트는 변화가 없게끔 하기 위해서 사용
    const client = useRef( null );

    // 1️⃣3️⃣ 서버에게 받은 메시지들을 저장하는 상태변수
    const [ messages , setMessages ] = useState( [] );

    // 5️⃣ 해당 컴포넌트 생명주기 , 1번 실행
    useEffect( () => {
        getMyInfo();

        // 9️⃣ 웹소켓 연결하기 ( new Client ( { } ) )
        const stomp = new Client ( {
            brokerURL : "ws://localhost:8080/ws", // <- 벡앤드의 .addEndpoint("/ws") 보고 적는 것
            reconnectDelay : 5000 , // 연동 실패 시 5초 마다 재연동
            onConnect: () => { // 연동 성공 시 실행할 로직 (함수)
                console.log("백엔드 소켓과 클라이언트 소켓 연결 성공")
            

                // 1️⃣4️⃣ 메시지 구독 (클라이언트 <-- 서버)
                // stomp.subscribe( "구독주소" , (받은메시지) => {} );
                    // 구독주소는 백엔드(스프링)의 소켓컨트롤러(@SendTo)에서 설정한 주소
                stomp.subscribe("/topic/message" , ( 메시지 ) => {
                    console.log( 메시지 );
                    // 1) 서버에 받은 메시지를 JSON으로 변환. (AXIOS는 자동으로 해준다. Socket은 자동으로 안 해준다.)
                    const data = JSON.parse(메시지.body); // JSON.parse( 문자 ) 문자->객체 , JSON.stringify( 객체 ) 객체->문자
                    // 2) 서버에게 받은 메시지를 상태변수에 대입한다.
                    messages.push( data );
                    setMessages( [...messages] ); //⭐ 얕은 비교를 하기 때문에 `[...배열명]` `{...객체명}` 필수 ⭐
                });

            }
            //, onStompError:(e)=>{console.log(e)} 에러 생기면 알려주는 것
        });

        // 🔟 웹소캣 실행 (.active();)
        stomp.activate();

        // 1️⃣1️⃣ 웹소캣을 안전하게 useRef로 보관한다.
        client.current = stomp;
            // useRef의 값은 current라는 속성에 포함된다.
            // => 수정 시 `.current = 새로운값` 해줘아 한다.

        // 1️⃣2️⃣ 컴포넌트가 언마운트될 때 (컴포넌트 생명주기: 마운트(탄생)-업데이트(렌더링)-언마운트(죽음))
        return () => {
            // 언마운트(화면에서 컴포넌트가 사라짐) 실행문;
            stomp.deactivate(); // 안전하게 소켓 닫기/종료
        }
    }, [] );

    // 2️⃣ 입력받은 값을 저장하는 상태변수
    const [ sendMsg , setSendMsg ] = useState('');

    // 1️⃣ 전송 버튼 클릭 시 입력받은 값 가져오기 (= 서버에게 채팅 메시지 보내기)
    const sendMessage = () => {
        
        // 1) 입력받은 값 확인하기
        console.log( '입력받은 값:', sendMsg );
        console.log( '인간 이름:', loginUser.mname);

        // 2) 만약에 입력한 값이 없으면 메시지 전송 안 함
        if(sendMsg == ''){
            alert('메시지 입력하세요.'); 
            return;
        }

        // 1️⃣6️⃣ 서버에 보낼 메시지 구성 , sender 보낸 사람 , message 입력받은 값
        const obj = { sender : loginUser.mname , message : sendMsg }
        console.log(client.current)

        // 1️⃣5️⃣ 연동된 소켓에 메시지 보내기 , useRef의 상태값 호출. `.current`
        // stomp.publish( { destination : "메시지받을서버주소" , body : 내용물  } )
        client.current.publish( {       // client.current == stomp(웹소켓) 1️⃣1️⃣에서 보관했기 때문에
            destination: "/app/chat",       // 메시지를 받을 서버의 주소, 스프링 소켓컨트롤러(@MessageMapping)에서 설정한 것
            body: JSON.stringify( obj ) // 보낼 메시지를 JSON 형식의 문자열 타입, *AXIOS는 자동*
        } );
        setSendMsg(''); // 메시지 전송 후 입력상자 초기화
    }

    
    // ⌨️ 이건 그냥 내가 만든 것 (Enter로 전송)
    const enter = (e) => {
        if(e.key === 'Enter'){
            sendMessage();
        }
    }


    return(
        <div>
            <h3> 채팅 </h3>
             <div className="contents" style={ {width : "300px"}}>

                {/* 받은 메시지들이 저장된 messages 출력하기 */}
                {
                    messages.map( ( msg ) => {
                        return (
                            <div className="msgbox"> 
                                { /* 삼항연산자 이용한 html 분기 */}
                                { loginUser && loginUser.mname == msg.sender ? 
                                    (<>
                                        <div className="msg" style={ { textAlign : "right" }}> { msg.message } </div> 
                                    </>) : 
                                    (<>
                                          <div className="sender" style={ { fontSize : "14px" , paddingTop: "8px"}}> { msg.sender } </div>
                                          <div className="msg"> { msg.message } </div>
                                    </>) 
                                }
                            </div>
                        )
                    })
                }
            </div>
            {/* 
            - 입력상자 내 value 속성에 상태변수 대입 시 입력이 불가능(입력은되는데렌더링이안됨)하다.
            - 상태변수는 렌더링해야 하기 때문.
            - 방안: onChange = { (e) => { setXXX( e.target ) }}
                +) 밑 코드 해석: e.target은 input의 value를 가지고 와라 그거로 Change 하겠다는 애
            */}
            <input value={ sendMsg } onKeyDown={enter} onChange={ (e) => {setSendMsg(e.target.value) }}/>
            <button onClick={sendMessage}> 전송 </button>
        </div>
    )
}