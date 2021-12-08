package Day8

import readInput
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor

fun main() {

    fun part2(input: List<String>): Int {

        var ans=0
        fun String.alphabetized() = String(toCharArray().apply { sort() })

        for(item in input)
        {
            var(first,second) = item.split("|")
            second = second.trim()

            val seq = first.split(" ").toMutableList()

            for(i in seq.indices)
            {
                seq[i] = seq[i].trim()
            }

            val codes = second.split(" ").toMutableList()
            for(i in codes.indices)
            {
                codes[i] = codes[i].trim()
            }

            val myMap:MutableMap<String,String> = mutableMapOf()

            var one= ""
            var four=""
            var eight=""
            var seven=""

            for(item in seq)
            {
                if(item.length==2)
                {
                    one=item

                    myMap[item.alphabetized()]="1"
                }
                if(item.length==4)
                {
                    four=item
                    myMap[item.alphabetized()]="4"

                }
                if(item.length==7)
                {
                    eight=item;
                    myMap[item.alphabetized()]="8"

                }
                if(item.length==3)
                {
                    seven=item
                    myMap[item.alphabetized()]="7"

                }
            }

            var six=""
            var zero=""
            var three=""
            var five=""
            var nine=""
            var two=""

            for(item in seq)
            {
                if(item.length==6)
                {
                    val a = item.contains(one[0])
                    val b = item.contains(one[1])

                    if(!(a && b))
                    {
                        myMap[item.alphabetized()]="6"
                        six=item
                        seq.remove(item)
                        break
                    }
                }
            }
            for(item in seq)
            {
                if(item.length==6)
                {
                    var isPossb=true
                    for(i in four)
                    {
                        if(!item.contains(i))
                        {
                            isPossb=false
                        }
                    }
                    if(isPossb)
                    {
                        myMap[item.alphabetized()]="9"
                        nine=item
                        seq.remove(item)
                        break
                    }
                }
            }

            for(item in seq)
            {
                if(item.length==6)
                {
                    myMap[item.alphabetized()]="0"
                    zero=item
                    seq.remove(item)
                    break
                }
            }

            for(item in seq)
            {
                if(item.length==5)
                {
                    var isPossb=true
                    for(i in seven)
                    {
                        if(!item.contains(i))
                        {
                            isPossb=false
                        }
                    }
                    if(isPossb)
                    {
                        myMap[item.alphabetized()]="3"
                        three=item
                        seq.remove(item)
                        break
                    }
                }
            }

            for(item in seq)
            {
                if(item.length==5)
                {
                    var isPossb=false

                    for(i in item)
                    {
                        if(!nine.contains(i))
                        {
                            isPossb=true
                        }
                    }

                    if(isPossb)
                    {
                        myMap[item.alphabetized()]="2"
                        two=item
                        seq.remove(item)
                        break

                    }
                }
            }

            for(item in seq)
            {
                if(item.length==5){
                myMap[item.alphabetized()]="5"
                five=item
                seq.remove(item)
                break}
            }
            //println(myMap.toString())
            var temp_ans=""

            for(item in codes)
            {
               // println(item.alphabetized())
                temp_ans=temp_ans.plus(myMap[item.alphabetized()])
            }
            ans+=temp_ans.toInt()




        }


        return ans
    }


    val testInput = readInput("Day8/Day08_test")
    val input = readInput("Day8/Day08")

    println(part2(testInput))
    println(part2(input))
}