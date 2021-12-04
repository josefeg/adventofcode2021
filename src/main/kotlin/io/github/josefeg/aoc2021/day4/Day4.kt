package io.github.josefeg.aoc2021.day4

import java.io.File

data class Number(val value: Int, val marked: Boolean = false)

data class Line(val numbers: List<Number>) {
    companion object {
        fun parse(line: String): Line {
            val numbers = line.split(" ")
                .filter { it.isNotEmpty() }
                .map { it.toInt() }
                .map { Number(it) }
            return Line(numbers)
        }
    }

    fun mark(value: Int): Line {
        return Line(this.numbers.map { if (it.value == value) Number(value, true) else it })
    }

    fun allMarked(): Boolean {
        return this.numbers.find { !it.marked } == null
    }

    fun sumUnmarked(): Int {
        return this.numbers.filter { !it.marked }
            .fold(0) { acc, n -> acc + n.value }
    }
}

data class Board(val lines: List<Line>) {
    companion object {
        fun parse(lines: List<String>) = Board(lines.filter { it.isNotEmpty() }.map { Line.parse(it) })
    }

    fun sumUnmarked(): Int {
        return this.lines.fold(0) { acc, line -> acc + line.sumUnmarked() }
    }

    fun mark(value: Int): Board {
        return Board(this.lines.map { it.mark(value) })
    }

    fun hasWon(): Boolean {
        val hasWinningLine = this.lines.find { it.allMarked() } != null
        return if (hasWinningLine) true else this.hasWinningColumn()
    }

    private fun hasWinningColumn(): Boolean {
        val columns = (0..4).map { index -> this.lines.fold(true) { acc, line -> acc && line.numbers[index].marked } }
        return columns.find { it } != null
    }
}

fun parseInput(input: List<String>): Pair<List<Int>, List<Board>> {
    val drawnNumbers = input[0].split(",").map { it.toInt() }
    val boards = input.subList(2, input.size)
        .chunked(6)
        .map { Board.parse(it) }

    return Pair(drawnNumbers, boards)
}

fun findFirstWinningNumberAndBoardCombination(drawnNumbers: List<Int>, boards: List<Board>): Int {
    val markedBoards = boards.map { it.mark(drawnNumbers.first()) }
    val winningBoard = markedBoards.find { it.hasWon() }
    return if (winningBoard != null) {
        drawnNumbers.first() * winningBoard.sumUnmarked()
    } else {
        findFirstWinningNumberAndBoardCombination(drawnNumbers.subList(1, drawnNumbers.size), markedBoards)
    }
}

fun findLastWinningNumberAndBoardCombination(drawnNumbers: List<Int>, boards: List<Board>): Int {
    val remainingBoards = boards.map { it.mark(drawnNumbers.first()) }
        .filter { !it.hasWon() }

    return if (remainingBoards.size > 1) {
        findLastWinningNumberAndBoardCombination(drawnNumbers.subList(1, drawnNumbers.size), remainingBoards)
    } else {
        return findFirstWinningNumberAndBoardCombination(drawnNumbers.subList(1, drawnNumbers.size), remainingBoards)
    }
}

fun main() {
    val input = File("src/main/resources/day4.txt")
        .readLines()

    val (numbers, boards) = parseInput(input)
    println(findFirstWinningNumberAndBoardCombination(numbers, boards))
    println(findLastWinningNumberAndBoardCombination(numbers, boards))
}
