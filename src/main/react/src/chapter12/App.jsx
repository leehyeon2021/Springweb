import { Route, Routes } from "react-router-dom";
import TopNavi from "./components/TopNavi";
import Lifecycle from "./components/Lifecycle";
import LocalJsonFetcher from "./components/LocalJsonFetcher";

export default function App( props ){
    return(<>
        <TopNavi></TopNavi>
        {/* 새로운 컴포넌트(페이지) 만들면 라우터에 경로 연결 */}
        <Routes>
            <Route path="/" element={<Lifecycle/>}></Route>
            <Route path="/local" element={<LocalJsonFetcher/>}></Route>
        </Routes>
    </>)
}