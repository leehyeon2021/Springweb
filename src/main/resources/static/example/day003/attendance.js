// 1. POST
const POST = async () => {
    try{
        const obj = { "ano": 1, "studentName": "홍길동", "date": "2026-02-26", "status": "출석" };
        const response = await axios.post(`/attendance`, obj);
        console.log( response.data );
    }catch (e){console.log(e);}
}

// 2. GET 출석 전체조회
const GET1 = async () =>{
    try{
        const response = await axios.get(`/attendance`);
        console.log( response.data );
    }catch (e){console.log(e);}
}

// 3. GET2 출석 개별조회
const GET2 = async( ) => {
    try{
        const response = await axios.get(`/attendance/detail?ano=1`);
        console.log( response.data );
    }catch( e ){ console.log(e) }
}

// 4. PUT
const PUT = async( ) => {
    try{
        const obj = { "ano": 1, "studentName": "홍길동", "date": "2026-02-26", "status": "지각" };
        const response = await axios.put(`/attendance?ano=1`, obj);
        console.log( response.data );
    }catch( e ){ console.log(e) }
}

// 5. DELETE
const DELETE = async( ) => {
    try{
        const response = await axios.delete(`/attendance?ano=1`);
        console.log( response.data );
    }catch( e ){ console.log(e) }
}