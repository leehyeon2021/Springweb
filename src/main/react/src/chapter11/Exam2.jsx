import { Route, Routes } from "react-router-dom";
import TopNavi from "./components/TopNavi";
import Home from "./components/Home";
import NotFound from "./components/NotFound";
import CommonLayout from "./components/CommonLayout";
import LayoutIndex from "./components/LayoutIndex";
import RouterHooks from "./components/RouterHooks";

// Exam2.jsx
export default function Exam2( props ){
    return(<> 
        <TopNavi></TopNavi>
        <Routes> {/* 라우터들 설정 시작 */}
            <Route path="/" element={<Home />}/>    {/* 라우터 설정 */}

            {/* 중첩 라우터 (Outlet연습중)*/}
            <Route path="/intro" element={<CommonLayout/>}> {/* 상위 라우터 */}
                {/* 별도의 경로가 없을 때 index로 지정. 즉 intro인트로임. */}
                <Route index element={<LayoutIndex/>}/>     {/* 하위 라우터 */}

                <Route path="router" element={<RouterHooks/>}/>

            </Route>

            <Route path="*" element={<NotFound />}/> {/* 라우터 설정 */}
        </Routes>
    </>)
}