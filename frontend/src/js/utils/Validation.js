import REG from "../constant/REG";

const Validation = {
    isNum: value => {
        return REG.positiveFloat.test(value) || REG.negativeFloat.test(value)
    },
    isNegative:value=>{
        return REG.negativeFloat.test(value)
    },
    isLessEqualThan:(value,max)=>{
        return parseFloat(value) <= parseFloat(max)
    }
}

export default Validation