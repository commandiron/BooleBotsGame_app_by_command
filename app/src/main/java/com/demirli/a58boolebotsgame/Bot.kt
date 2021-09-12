package com.demirli.a58boolebotsgame

class Bot(
    var botName: String,
    var booleanValue: Boolean,
    var booleanOperation: String,
    var isAlive: Boolean = true
    //var speed: Int,
    //var direction: String

    //Sırada speed ve direction koyarak 8x8 bir tablo üzerine bunları hareket ettirme ve kesiştikleri yerde savaştırıp, alive true kalanları aynı speed ve direcyion ile devam edecek
    // alive false olanlar silinecek. Yapılacak bu kaldı.

) {
    companion object{
        fun booleanOperationAnd() = "AND"
        fun booleanOperationOr() = "OR"
        fun booleanOperationXor() = "XOR"

        fun collision(botA: Bot, botB: Bot ): String{

            var resultForA = false
            var resultForB = false

            println(botA.botName + " vs " + botB.botName)

            when(botA.booleanOperation){
                "AND" -> {
                    resultForA = botA.booleanValue and botB.booleanValue
                }
                "OR" -> {
                    resultForA = botA.booleanValue or botB.booleanValue
                }
                "XOR" -> {
                    resultForA = botA.booleanValue xor botB.booleanValue
                }
            }

            when(botB.booleanOperation){
                "AND" -> {
                    resultForB = botB.booleanValue and botA.booleanValue
                }
                "OR" -> {
                    resultForB = botB.booleanValue or botA.booleanValue
                }
                "XOR" -> {
                    resultForB = botB.booleanValue xor botA.booleanValue
                }
            }

            if(resultForA == resultForB){
                return "Tie"
            }else if(resultForA != resultForB){
                botA.isAlive = resultForA
                botB.isAlive = resultForB
                if(resultForA == true){
                    return botA.botName
                }else if(resultForB == true){
                    return botB.botName
                }
            }
            return ""
        }
    }
}