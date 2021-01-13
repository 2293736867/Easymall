import Utils from "./Utils";

const UserUtils = {
    getShowJSONFromArray:arr=>{
        return{
            id:arr[0],
            username:arr[1],
            nickname:arr[2],
            email:arr[3],
        }
    },
    getAddJSONFromArray:arr=>{
        return{
            username:arr[0],
            nickname:arr[1],
            email:arr[2],
            password:Utils.sha3(arr[3])
        }
    },
    getPreModifyArrayFromJSON:u=>{
        return [u.id,u.username,u.nickname,u.email]
    },
    setModifiedJSONFromArray:(row,arr)=>{
        row.username = arr[1]
        row.nickname = arr[2]
        row.email = arr[3]
    },
    getModifyJSONFromArray:arr=>{
        return{
            id:arr[0],
            username:arr[1],
            nickname:arr[2],
            email:arr[3],
        }
    },
}

export default UserUtils