/**
 * This is my first object!
 * @type {{firstName: string, lastName: string, age: number, address: {global: string, location: string}, equipment: [string,string,string,number], Info: touhouGirl.Info, Attack: touhouGirl.Attack}}
 */

var touhouGirl = {
    firstName: 'Aya',
    lastName: 'Shameimaru',
    age: 1100,
    address: {
        global: "Gensokyo",
        location: 'Youkai Mountain'
    },
    equipment: [
        'spellcard',
        'notebook',
        'fan',
        21
    ],

    /**
     * @return {string}
     */
    Info: function () {
        return this.firstName + " " + this.lastName + ", age is " + this.age;
    },

    /**
     * @return {string}
     */
    Attack: function () {
        for (var i = 0; i < this.equipment.length; i++) {
            if (this.equipment[i] === 'fan') {
                return "Windy attack!";
            } else if (i === this.equipment.length - 1) {
                return "The attack didn't occur";
            }
        }
    }
};

console.log(touhouGirl.Info());
console.log("");
console.log(touhouGirl.Attack());