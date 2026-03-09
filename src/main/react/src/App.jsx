// 컴포넌트 쪼개는 이유: 유지보수 용이. (자바할 때 클래스 만드는 이유와 같음)

// 1. 프론트 관련 컴포넌트
function Frontcomp(){ // 생성방법1: function 컴포넌트명(){}
  return ( 
    <>
      <li>HTML5</li>
      <li>CSS3</li>
      <li>Javascript</li>
      <li>jQuery</li>
    </> 
  ) // 생성방법2: 컴포넌트내 return(<>HMTL코드</>)
}

// 2. 백엔드 관련 컴포넌트
const BackComp = () => {
  return(
    <>
      <li>java</li>
      <li>Oracle</li>
      <li>JSP</li>
      <li>Spring Boot</li>
    </>
  )
}

// 3. 폼 관련 컴포넌트
let FormComp = function(){
  return(
    <>
      <form>
        <select name="gubun">
            <option value="front">프론트엔드</option>
            <option value="back">백엔드</option>
        </select>
        <input type="text" name="title"/>
        <input type="submit" value="추가"/>
      </form>
    </>
  )
}

// 4. 여러 컴포넌트 호출/참조하는 컴포넌트
function App() {
  return (
    <>
      <h2> React-기본</h2>
      <ol>
        <Frontcomp></Frontcomp>
        <BackComp/>
        <BackComp/>
      </ol>
      <FormComp/>
    </>
  )
}

export default App