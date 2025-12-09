public class StockBuyAndSell {
  public static void main(String[] args) {
    int[] prices = { 7, 1, 5, 3, 6, 4 };

    int minPrice = Integer.MAX_VALUE;
    int maxProfit = 0;

    for (int price : prices) {
      // update minimum price so far
      if (price < minPrice) {
        minPrice = price;
      }

      // calculate profit if sold today
      int profit = price - minPrice;

      // update max profit
      if (profit > maxProfit) {
        maxProfit = profit;
      }
    }

    System.out.println("Max Profit : " + maxProfit);
  }
}
