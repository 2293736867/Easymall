import jsSHA from "jssha"

const Utils = {
    sha3:(text)=>{
        let encrypt = new jsSHA("SHA3-512","TEXT")
        encrypt.update(text)
        return encrypt.getHash('HEX')
    },
}

export default Utils