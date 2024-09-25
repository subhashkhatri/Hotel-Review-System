import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Hotel {
    private String name;
    private List<Review> reviews;

    public Hotel(String name) {
        this.name = name;
        this.reviews = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }
}

class Review {
    private String user;
    private int rating;
    private String comment;
    private String date;

    public Review(String user, int rating, String comment, String date) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }
}

class HotelReviewSystem {
    private List<Hotel> hotels;

    public HotelReviewSystem() {
        this.hotels = new ArrayList<>();
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public void addReviewToHotel(String hotelName, Review review) {
        Hotel hotel = findHotelByName(hotelName);
        if (hotel != null) {
            hotel.addReview(review);
            System.out.println("Review added successfully.");
        } else {
            System.out.println("Hotel not found.");
        }
    }

    public void printHotelReviews(String hotelName) {
        Hotel hotel = findHotelByName(hotelName);
        if (hotel != null) {
            List<Review> reviews = hotel.getReviews();
            if (reviews.isEmpty()) {
                System.out.println("No reviews available for this hotel.");
            } else {
                for (Review review : reviews) {
                    System.out.println("User: " + review.getUser());
                    System.out.println("Rating: " + review.getRating());
                    System.out.println("Comment: " + review.getComment());
                    System.out.println("-------------------------");
                }
            }
        } else {
            System.out.println("Hotel not found.");
        }
    }

    public void sortReviewsByRating(String hotelName) {
        Hotel hotel = findHotelByName(hotelName);
        if (hotel != null) {
            List<Review> reviews = hotel.getReviews();
            Collections.sort(reviews, Comparator.comparingInt(Review::getRating).reversed());
            System.out.println("Reviews sorted by rating:");
            printHotelReviews(hotelName);
        } else {
            System.out.println("Hotel not found.");
        }
    }

    public void sortReviewsByDate(String hotelName) {
        Hotel hotel = findHotelByName(hotelName);
        if (hotel != null) {
            List<Review> reviews = hotel.getReviews();
            Collections.sort(reviews, Comparator.comparing(Review::getDate));
            System.out.println("Reviews sorted by date:");
            printHotelReviews(hotelName);
        } else {
            System.out.println("Hotel not found.");
        }
    }

    public void filterReviewsByRating(String hotelName, int minRating) {
        Hotel hotel = findHotelByName(hotelName);
        if (hotel != null) {
            List<Review> reviews = hotel.getReviews();
            List<Review> filteredReviews = new ArrayList<>();
            for (Review review : reviews) {
                if (review.getRating() >= minRating) {
                    filteredReviews.add(review);
                }
            }
            if (filteredReviews.isEmpty()) {
                System.out.println("No reviews available for this hotel with a rating of " + minRating + " or higher.");
            } else {
                System.out.println("Filtered reviews with a rating of " + minRating + " or higher:");
                for (Review review : filteredReviews) {
                    System.out.println("User: " + review.getUser());
                    System.out.println("Rating: " + review.getRating());
                    System.out.println("Comment: " + review.getComment());
                    System.out.println("-------------------------");
                }
            }
        } else {
            System.out.println("Hotel not found.");
        }
    }

    private Hotel findHotelByName(String hotelName) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equalsIgnoreCase(hotelName)) {
                return hotel;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        HotelReviewSystem reviewSystem = new HotelReviewSystem();

        // Add sample hotels
        Hotel MTR = new Hotel("MTR");
        Hotel EatNRepeat = new Hotel("EatNRepeat");

        reviewSystem.addHotel(MTR);
        reviewSystem.addHotel(EatNRepeat);

        // Add sample reviews
        Review review1 = new Review("Ram", 4, "Great experience!", "2023-12-09");
        Review review2 = new Review("Krishna", 5, "Excellent service!", "2023-12-08");

        reviewSystem.addReviewToHotel("MTR", review1);
        reviewSystem.addReviewToHotel("EatNRepeat", review2);

        // User input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n----- Hotel Review System -----");
            System.out.println("1. Add a review to a hotel");
            System.out.println("2. Print reviews of a hotel");
            System.out.println("3. Sort reviews of a hotel by rating");
            System.out.println("4. Sort reviews of a hotel by date");
            System.out.println("5. Filter reviews of a hotel by minimum rating");
            System.out.println("0. Exit");
            System.out.print("\nEnter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter the hotel name: ");
                    String hotelName = scanner.nextLine();
                    System.out.print("Enter your name: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter your rating (1-5): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter your comment: ");
                    String comment = scanner.nextLine();

                    Review newReview = new Review(userName, rating, comment, getCurrentDate());
                    reviewSystem.addReviewToHotel(hotelName, newReview);
                    break;
                case 2:
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter the hotel name: ");
                    String hotelToPrint = scanner.nextLine();

                    reviewSystem.printHotelReviews(hotelToPrint);
                    break;
                case 3:
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter the hotel name: ");
                    String hotelToSortByRating = scanner.nextLine();

                    reviewSystem.sortReviewsByRating(hotelToSortByRating);
                    break;
                case 4:
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter the hotel name: ");
                    String hotelToSortByDate = scanner.nextLine();

                    reviewSystem.sortReviewsByDate(hotelToSortByDate);
                    break;
                case 5:
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter the hotel name: ");
                    String hotelToFilter = scanner.nextLine();
                    System.out.print("Enter the minimum rating: ");
                    int minRating = scanner.nextInt();

                    reviewSystem.filterReviewsByRating(hotelToFilter, minRating);
                    break;
                case 0:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static String getCurrentDate() {
        // Implement code to get the current date in the desired format
        return "2023-12-10";
    }
}
