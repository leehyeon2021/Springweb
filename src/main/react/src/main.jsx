/* (260310)
1. index.html( 싱글페이지 )에서 root라는 id갖는 div호출
const root = document.querySelector('#root');

2. root 마크업에 랜더링(render)
createRoot( root ).render( <h1> 안녕하세요! </h1> );
vs root.innerHTML = "<h1> 안녕하세요! </h1>";   */

import { createRoot } from 'react-dom/client'
import './index.css'

//import App from './App.jsx'
//createRoot( document.getElementById('root') ).render( <App /> ) <-260310에 주석처리함.

/* (260310)
- chapter4 예제
import Exam1 from './chapter4/Exam1.jsx' // 컴포넌트 불러오기
const root = document.querySelector('#root') // index.html에서 root 가져오기
createRoot( root ).render( <Exam1 /> ) // root에 최초 컴포넌트 렌더링하기
    // createRoot( document.getElementById('#root') ).render( <Exam1 /> ) <-위랑 이거랑 같음. 그냥 쪼개놓은 것.

- chapter5 예제로 바꾸기
import Exam2 from './chapter5/Exam2'
createRoot(document.getElementById('root')).render(<Exam2/>)*/

/* (260311)
- chapter6 예제 
import Exam1 from './chapter6/Exam1.jsx';
createRoot( root ).render( <Exam1/> );

- chapter7 예제 
import Exam2 from './chapter7/Exam2.jsx'
createRoot(root).render(<Exam2/>)*/

/* (260312) 
- chapter8 예제 
import Exam1 from './chapter8/Exam1'
createRoot(root).render(<Exam1/>) 

- chapter9 예제
import Exam2 from './chapter9/Exam2'
createRoot(root).render(<Exam2/>)*/

/* (260331)
- chapter10 예제(얕은비교)
import Exam1 from './chapter10/Exam1'
createRoot(root).render(<Exam1/>)

- chapter11 예제(라우터)
import Exam2 from './chapter11/Exam2'
import {BrowserRouter} from 'react-router-dom'                   // 1. 라이브러리 import 하기
createRoot(root).render(                                         // 2. 최초 렌더링 되는 컴포넌트에 BrowserRouter 감싼다.
    <BrowserRouter>
        <Exam2/>
    </BrowserRouter>
) */

/* (260401) 
- chapter12 예제 (생명주기)
import App from './chapter12/App'
import { BrowserRouter } from 'react-router-dom'
createRoot(root).render(
    <BrowserRouter>
        <App/>
    </BrowserRouter>
)*/

/* (260402) 
- practice1 예제 */
import App from './practice1/App';
import {BrowserRouter} from 'react-router-dom'
createRoot( root ).render(
    <BrowserRouter>
        <App />
    </BrowserRouter>
)