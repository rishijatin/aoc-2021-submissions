fun main() {
    fun part1(input: List<String>): Int {
        var previousValue = Int.MAX_VALUE
        var depthIncreases = 0

        for(item in input)
        {
            if(item.toInt() > previousValue)
            {
                depthIncreases+=1
            }
            previousValue = item.toInt()
        }

        return  depthIncreases
    }

    fun part2(input: List<String>): Int {

        val slidingSum: ArrayList<Int> = arrayListOf()

        var ptr1 = 0
        var ptr2 = 0
        var sum =0

        for(i in 0..2)
        {
            if(i<input.size)
            {
                sum+= input[i].toInt()
                ptr2++
            }
            else
            {
                break
            }
        }
        slidingSum.add(sum)

        while(ptr2<input.size)
        {
            sum-=input[ptr1].toInt()
            ptr1++

            sum+=input[ptr2].toInt()
            ptr2++
            slidingSum.add(sum)
        }
        var previousValue = Int.MAX_VALUE
        var depthIncreases = 0

        for(item in slidingSum)
        {
            if(item > previousValue)
            {
                depthIncreases+=1
            }
            previousValue = item
        }
        return  depthIncreases

    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
