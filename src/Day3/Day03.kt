package Day3

import readInput

fun main() {

    fun convertToDecimal(input:String):Int{

        var converted=0

        for(i in input.indices)
        {
            if(input[i] == '0')
            {
                converted*=2
            }
            else
            {
                converted*=2
                converted++
            }
        }

        return converted

    }

    fun part1(input: List<String>): Int {

        val msbList: MutableList<Int> = MutableList(input[0].length) {0}

        for(i in input.indices)
        {
            for(j in input[i].indices) {
                if (input[i][j].equals('0')) {
                    msbList[j]--
                } else {
                    msbList[j]++
                }
            }
        }
        var gammaRate=0

        for(i in msbList.indices)
        {
            if(msbList[i]>0)
            {
                gammaRate*=2
                gammaRate+=1
            }
            else
            {
                gammaRate*=2
            }
        }

        var epsilonRate=0

        for(i in msbList.indices)
        {
            if(msbList[i]<0)
            {
                epsilonRate*=2
                epsilonRate+=1
            }
            else
            {
                epsilonRate*=2
            }
        }

        return gammaRate*epsilonRate
    }

    fun part2(input: List<String>): Int {

        var oxygenGeneratorRating=0
        var co2ScrubberRating=0
        var numLeft = input.size
        val discardedValues:MutableList<Boolean> = MutableList(input.size){false}
        for(i in input[0].indices)
        {
            var dominator=0
            for(j in input.indices)
            {
                if(!discardedValues[j])
                {
                    if(input[j][i]=='0')
                    {
                        dominator--
                    }
                    else
                    {
                        dominator++
                    }
                }

            }
            val considerationBit=when{
                  dominator>0 -> '1'
                  dominator<0 -> '0'
                  else -> '1'
                            }
                for(j in input.indices)
                {
                    if(input[j][i] !=considerationBit && !discardedValues[j]) {
                        discardedValues[j]=true
                        numLeft--
                    }
                }

                if(numLeft==1)
                {
                    break
                }
            }

        for(i in discardedValues.indices)
        {
            if(!discardedValues[i])
            {
                oxygenGeneratorRating = convertToDecimal(input[i])
            }
            discardedValues[i]=false
        }

        numLeft=input.size

        for(i in input[0].indices)
        {
            var dominator =0

            for(j in input.indices)
            {
                if(!discardedValues[j])
                {
                    if(input[j][i]=='0')
                    {
                        dominator--
                    }
                    else{
                        dominator++
                    }
                }
            }
            val considerationBit=when{
                dominator>0 -> 0
                dominator<0 -> 1
                else -> 0
            }
            for(j in input.indices)
            {
                if(input[j][i]-'0' !=considerationBit && !discardedValues[j]) {
                    numLeft--
                    discardedValues[j]=true
                }

            }
            if(numLeft==1)
            {
                break
            }
        }

        for(i in discardedValues.indices)
        {
            if(!discardedValues[i])
            {
                co2ScrubberRating = convertToDecimal(input[i])
                discardedValues[i]=false
            }
        }
        return  oxygenGeneratorRating*co2ScrubberRating

    }

    val input = readInput("Day3/Day03")
    val testInput = readInput("Day3/Day03_test")

    println(part1(testInput))
    println(part2(testInput))
    println(part1(input))
    println(part2(input))
}
