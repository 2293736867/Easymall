import jsSHA from "jssha"

const Utils = {
    sha3:(text)=>{
        let encrypt = new jsSHA("SHA3-512","TEXT")
        encrypt.update(text)
        return encrypt.getHash('HEX')
    },
    getCharts(id, width, height)
    {
        let element = document.getElementById(id)
        Object.defineProperty(element,'clientWidth',{get:function(){return width;}})
        Object.defineProperty(element,'clientHeight',{get:function(){return height;}})
        return element
    },
}

export default Utils