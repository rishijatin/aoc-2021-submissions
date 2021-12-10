package Day10

import readInput

import java.math.BigInteger


fun main() {
    fun addVal(item:Char):Int {
        return when(item)
        {
            ')' -> 1
            ']' -> 2
            '}' -> 3
            '>' -> 4
            else -> 0

        }
    }

    fun revValue(item:Char):Char{
        return when(item)
        {
            ')' -> '('
            ']' -> '['
            '}' -> '{'
            '>' -> '<'
            else -> '.'

        }
    }

    fun revValue2(item:Char):Char{
        return when(item)
        {
            '(' -> ')'
            '[' -> ']'
            '{' -> '}'
            '<' -> '>'
            else -> '.'

        }
    }

    fun part2(input: List<String>): BigInteger {
        var ans=0

        val scores:ArrayList<BigInteger> = arrayListOf()

        for(item in input)
        {   val st:ArrayList<Char> = arrayListOf()
            var consider:Boolean = true
            for(j in item.indices)
            {
                if(item[j]=='[' || item[j]=='(' || item[j]=='{' || item[j]=='<')
                {
                    st.add(item[j]);
                }
                else
                {
                    if(st.isEmpty())
                    {
                        ans+=addVal(item[j])
                        consider=false
                        break
                    }
                    else
                    {
                        if(st[st.size-1]!=revValue(item[j]))
                        {
                            ans+=addVal(item[j])
                            consider=false
                            break
                        }
                        else
                        {
                            st.removeLast()
                        }
                    }
                }
            }

            if(consider)
            {
                var tempAns=0.toBigInteger()
                //println(st.reversed())
                for(item in st.reversed())
                {
                    tempAns*=5.toBigInteger()
                    tempAns+=addVal(revValue2(item)).toBigInteger()
                }
                if(tempAns>0.toBigInteger())
                {scores.add(tempAns)}
            }
        }

        scores.sort()

        //println(scores.toString())

        return scores[scores.size/2]
    }


    val testInput = readInput("Day10/Day10_test")
    val input = readInput("Day10/Day10")

    println(part2(testInput))
    println(part2(input))
}