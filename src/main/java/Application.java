import com.leetcode.LC_76_Minimum_Window_Substring;

public class Application {
	public static void main(String[] args) {
		LC_76_Minimum_Window_Substring solution = new LC_76_Minimum_Window_Substring();

		LC_76_Minimum_Window_Substring.testValues().stream()
				.forEach(test -> {
					String result = solution.minWindow(test.get(0), test.get(1));

					if (!test.get(2).equals(result))
						System.out.println("Failed test - input = " + test.get(0)
											+ ", pattern = [" + test.get(1) + "]"
											+ ", result = [" + test.get(2) + " == " + result + "]");
				});
	}
}
