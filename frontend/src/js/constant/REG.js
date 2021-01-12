const REG = {
    username:/^[a-zA-Z0-9]{1,25}$/,
    email:/[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+/,
    password:/^\S*(?=\S{6,})(?=\S*\d)(?=\S*[A-Z])(?=\S*[a-z])(?=\S*[!@#$%^&*? ])\S*$/,
    positiveFloat:/^\d+(\.\d+)?$/,
}

export default REG