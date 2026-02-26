// 1. POST

// 3. 출석 개별조회
const GET2 = async( ) => {
    try{
        const response = await axios.get("/attendance/detail");
        console.log( response.data );
    }catch( e ){ console.log(e) }
}
GET2();