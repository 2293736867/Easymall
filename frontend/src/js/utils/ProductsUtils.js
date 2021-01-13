const ProductsUtils={
    getShowJSONFromArray:arr=>{
        return{
            id:arr[0],
            name:arr[1],
            price:arr[2],
            rating:arr[3],
            freight:arr[4],
            category:arr[5],
            num:arr[6],
            description:arr[7]
        }
    },
    setModifiedJSONFromArray:(row,arr)=>{
        row.name = arr[1]
        row.price = arr[2]
        row.rating = arr[3]
        row.freight = arr[4]
        row.category = arr[5]
        row.num = arr[6]
        row.description = arr[7]
    },
    getAddJSONFromArray:arr=>{
        return {
            name: arr[0],
            price: arr[1],
            rating: arr[2],
            freight: arr[3],
            category: arr[4],
            num: arr[5],
            description: arr[6]
        }
    },
    getModifyJSONFromArray:arr=>{
        return{
            name:arr[1],
            price:arr[2],
            rating:arr[3],
            freight:arr[4],
            category:arr[5],
            num:arr[6],
            description:arr[7]
        }
    },
    getPreModifyArrayFromJSON:p=>{
        return [p.id,p.name,p.price,p.rating,p.freight,p.category,p.num,p.description]
    },
}

export default ProductsUtils