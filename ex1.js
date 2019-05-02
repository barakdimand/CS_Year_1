function first_func() {
    let finalObject = {
        name: "does not have a name yet",
        age: null,
        infinity : false,
        GPA: null,
        GPA_pass : null,
        work_bool : false,
        type_bool: null,
        birthYear : 1995,
        return_string : "This does not work yet"
    }
    // 1. Using the const to define the myname, myname will not be able to be changed throughout the function, but
    // the finalObject name field gets changed to myname and will be able to be changed (unlike const)
    const myname = "Barak Dimand"
    // assign the const name to the final object name field.
    finalObject.name = myname

    // 2. using try/catch from the tutorials to catch an exception from trying to perform alert
    try {
        alert("This should throw an exception!!!!, but it wont ;)")
    } catch (err) {
        console.log("Avoided exception thanks to catch")
    }

    // 3. using the flexibility of strings and numbers in JS
    let birthYear = "1995"
    let currentYear = "2019"
    let age = null
    age = currentYear - birthYear
    finalObject.age = age

    // 4. use the fact that js can recognize num/0 = infinity and not error
    // and uses a random number generator to estimate my GPA
    let num = 1
    let bigNum = num/0
    // if bigNum really equals infinity then change object inifinity to true
    if (bigNum > 10000000) {
        finalObject.infinity = true
    }
    function getRandomInt(max) {
        return Math.floor(Math.random() * Math.floor(max));
    }
    function doMoedBet() {
        return getRandomInt()
    }
    let GPA = getRandomInt()
    let test = 1
    if (GPA < 60) {
        while (GPA < 60)
        GPA = doMoedBet()
        test++
    }
    // create new final object field for tests taken till passed
    finalObject.tests = test
    finalObject.GPA = GPA
    finalObject.GPA_pass = true

    // 5. cast boolean values into strings
    let bool = true
    let boolstring = String(bool)
    finalObject.work_bool = boolstring
    finalObject.type_bool = typeof boolstring

    // 6. using the ` ` to insert variables into a string
    let newString = "The function successfully ran through 100 lines of code"
    let finalNewString = `${finalObject.work_bool}, ${newString}`
    finalObject.return_string = finalNewString

    // 7. using the time
    function sayHi() {
        console.log('\n\n\nHello 100% on My first assignement as a Web Developer');
    }
    setTimeout(sayHi, 3000);

    // 8. perform the js built in Date function to update the date of when this function was ran
    // and add the date as a new parameter of the object
    let date = new Date()
    date = date.toString()
    year = date.substring(11,15)

    // now date has the current year so we will override age according to current year
    let currage = null
    currage = year - finalObject.birthYear
    const oldest = 120
    if (currage > oldest) {
        finalObject.isAlive = false
        finalObject.isAlive.reason = "This person cannot be alive since he is older than 120 years"
    } else {
        finalObject.isAlive = true
        finalObject.isAlive.reason = "This person is younger than 120"
    }

    // Put together the final String that this function will return
    let solution = ``
    let one = finalObject.work_bool
    let two = `I am ${finalObject.age} and I am alive (${finalObject.isAlive})`
    let three = newString
    solution = `${one}, ${two} \n${three}`
    return solution
}

function second_func() {
    return "1. using the const, the 'myname' will not be able to change throughout the function. " +
        "2. using try/catch to catch an exception from trying to perform alert " +
        "3. using the flexibility of strings and numbers in JS I perform arithmetic expression on strings" +
        "4. using the fact that js can recognize num/0 = infinity and not error\n I compare a big number to infinity" +
        "       also, I used a random number generator to estimate my GPA" +
        "5. I showed that I can cast boolean values into strings" +
        "6. using the ` ` to insert variables into a string" +
        "7. I used the setTime function that exists in Js to add an extra line after the code is ran" +
        "8. I perform the js built in Date function to override age with date of when this function was ran\n" +
        "       and add the date as a new parameter of the object." +
        "       Also, adding new fields to object isAlive and a reason to why this is a correct boolean."
}




// This function will show the difference between defining a var and a let in a for loop
// When using var, the "k" will be changed to 3 after the for loop and therefore every function in the data array will return 3.
// When using let, the "k" will change in every spot in the array as let creates a new k every time (unlike the var)
function let_func() {
    var data = [];
    for (var k = 0; k< 3; k++) {
        data[k] = function () {
            console.log(k);
            return k
        };
    }
    return data
}

function let_func_desc() {
    return "This function will show the difference between defining a var and a let in a for loop\n " +
        "When using var, the \"k\" will be changed to 3 after the for loop and therefore every function in the data array will return 3.\n " +
        "When using let, the \"k\" will change in every spot in the array as let creates a new k every time (unlike the var)" +
        "Therefore when running it with a let we will get 0,1,2"
}

let q1 = function () {
    let obj1 = {
        'doSomething' : first_func(),
        'desc' : second_func()
    }
    return obj1
}

let q2 = function () {
    let obj2 = {
        'doSomething' : let_func(),
        'desc' : let_func_desc()
    }
    return obj2
}




function q3() {
    let obj = {
    'doSomething': function () {
        let array = new Array(10)
        let element = {k: "it works"};
        array[0] = element
        for (let i = 1; i < 10; i++) {
            array[i] = Object.create(array[i - 1])
        }
        return array
    },
        'desc' : "First the function dosomething creates a new array of size 10, " +
            "then the first element k is created and inserted into the array," +
            "then by using the Object.create function every element in the array is " +
            "created using the previous elements prototype."
    }
    return obj

}

function q4() {
    value = 0
    this.doSomething = function () {
        let calculator = {
            getValue: function () {
                return value
            },
            add: function (num) {
                value += num;
                return value
            },
            sub: function (num) {
                value -= num;
                return value
            }
        }
        return calculator
    }
    this.desc = "This is the constructor for q4 where calling new q4 creates an object with a doSomething function" +
        "that creates a calculator object with the methods add, sub, and getValue. The calculator is initialized" +
        "with value of zero, and then each method adds or subtracts or retrieves according to the value." +
        "To use the doSomething method, create an object of type q4 and use doSomething to create the calculator object," +
        "and then use the calculator methods in order to change the value of the calculator"
}



// This is the main testing code
let ans1 = q1()
console.log(ans1)

let ans2 = q2()
console.log(ans2)

let ans3 = q3()
console.log(ans3)

let ans4 = new q4()
let calc = ans4.doSomething()

console.log(ans4)
//console.log(calc.getValue())
//calc.add(10)
//calc.sub(13)
//console.log(calc.getValue())