import jsSHA from "jssha"

const encrypt = new jsSHA("SHA3-512", "TEXT", { encoding: "UTF8" });

const Utils = {
    sha3:(text)=>{
        encrypt.update(text)
        return encrypt.getHash('HEX')
    }
}

export default Utils