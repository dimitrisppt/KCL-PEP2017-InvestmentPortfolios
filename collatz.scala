// Part 1 about the 3n+1 conjecture
//=================================

object CW6a {

	//(1) Complete the collatz function below. It should
	//    recursively calculate the number of steps needed
	//    until the collatz series reaches the number 1.
	//    If needed, you can use an auxiliary function that
	//    performs the recursion. The function should expect
	//    arguments in the range of 1 to 1 Million.



	def rec(n: Long, i: Long): Long = {
		if (n == 1) {
		  i + 1
		} else if ((n % 2) == 0) {
		  rec(n/2, i + 1)
		} else {
		  rec(3*n + 1, i + 1)
		}
	}

	def collatz(n: Long): Long = rec(n, 0)


	//(2)  Complete the collatz-bound function below. It should
	//     calculate how many steps are needed for each number
	//     from 1 up to a bound and then calculate the maximum number of
	//     steps and the corresponding number that needs that many
	//     steps. Again, you should expect bounds in the range of 1
	//     up to 1 Million. The first component of the pair is
	//     the maximum number of steps and the second is the
	//     corresponding number.

	def collatz_max(bnd: Long) : (Long, Long) = {

	  val list = for (i <- (1L to bnd)) yield collatz(i)
	  val max = list.max
	  (max, list.indexOf(max)+1)

	}

}
