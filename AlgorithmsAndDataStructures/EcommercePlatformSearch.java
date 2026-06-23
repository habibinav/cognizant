import java.util.Arrays;

class Product implements Comparable<Product> {
    int productId;
    String productName;
    String category;

    Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public int compareTo(Product other) {
        return Integer.compare(this.productId, other.productId);
    }

    public String toString() {
        return "Product{id=" + productId + ", name='" + productName + "', category='" + category + "'}";
    }
}

public class EcommercePlatformSearch {

    public static Product linearSearch(Product[] products, int targetId) {
        for (Product p : products) {
            if (p.productId == targetId) return p;
        }
        return null;
    }

    public static Product binarySearch(Product[] products, int targetId) {
        int low = 0, high = products.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (products[mid].productId == targetId) return products[mid];
            else if (products[mid].productId < targetId) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }

    public static Product linearSearchByName(Product[] products, String name) {
        for (Product p : products) {
            if (p.productName.equalsIgnoreCase(name)) return p;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("=== E-commerce Platform Search Function ===\n");

        Product[] products = {
            new Product(105, "Laptop", "Electronics"),
            new Product(203, "Headphones", "Electronics"),
            new Product(42, "Running Shoes", "Sports"),
            new Product(310, "Coffee Maker", "Kitchen"),
            new Product(87, "Backpack", "Travel"),
            new Product(156, "Smartphone", "Electronics"),
            new Product(271, "Yoga Mat", "Sports"),
            new Product(15, "Water Bottle", "Kitchen"),
            new Product(189, "Desk Lamp", "Home"),
            new Product(333, "Sunglasses", "Fashion")
        };

        System.out.println("--- Linear Search (unsorted array) ---");
        Product found = linearSearch(products, 87);
        System.out.println("Search for ID 87: " + (found != null ? found : "Not found"));

        found = linearSearch(products, 999);
        System.out.println("Search for ID 999: " + (found != null ? found : "Not found"));

        found = linearSearchByName(products, "Laptop");
        System.out.println("Search by name 'Laptop': " + (found != null ? found : "Not found"));

        System.out.println("\n--- Binary Search (sorted array) ---");
        Product[] sorted = products.clone();
        Arrays.sort(sorted);
        System.out.println("Sorted catalog:");
        for (Product p : sorted) System.out.println("  " + p);

        found = binarySearch(sorted, 87);
        System.out.println("\nSearch for ID 87: " + (found != null ? found : "Not found"));

        found = binarySearch(sorted, 999);
        System.out.println("Search for ID 999: " + (found != null ? found : "Not found"));

        System.out.println("\n--- Performance Comparison ---");
        int size = 100000;
        Product[] largeArray = new Product[size];
        for (int i = 0; i < size; i++) {
            largeArray[i] = new Product(i, "Product" + i, "Category");
        }
        int target = size - 1;

        long start = System.nanoTime();
        linearSearch(largeArray, target);
        long linearTime = System.nanoTime() - start;

        start = System.nanoTime();
        binarySearch(largeArray, target);
        long binaryTime = System.nanoTime() - start;

        System.out.println("Array size: " + size);
        System.out.println("Linear Search time: " + linearTime + " ns");
        System.out.println("Binary Search time: " + binaryTime + " ns");
        System.out.println("Binary search is ~" + (linearTime / Math.max(binaryTime, 1)) + "x faster");

        System.out.println("\n=== Time Complexity Analysis ===");
        System.out.println("Algorithm       | Best   | Average  | Worst");
        System.out.println("----------------|--------|----------|--------");
        System.out.println("Linear Search   | O(1)   | O(n)     | O(n)");
        System.out.println("Binary Search   | O(1)   | O(log n) | O(log n)");
        System.out.println("\nConclusion: Binary search is far more efficient for sorted data.");
        System.out.println("For an e-commerce platform, keep product IDs sorted and use binary");
        System.out.println("search for ID lookups. Use linear search for unsorted fields (name, category).");
    }
}
