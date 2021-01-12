import REG from "../constant/REG";

const Validation = {
    num: (value, max) => {
        if (!value)
            return false
        if (REG.positiveFloat.test(value)) {
            const num = parseFloat(value)
            if (num >= 0.0) {
                if (typeof max !== 'undefined') {
                    return num <= max
                }
                return true
            }
            return false
        }
        return false
    }
}

export default Validation