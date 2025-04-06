import java.util.*;

class User {
    int userId;
    String name;
    int age;
    List<Integer> friendIds;
    User next;

    User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
        this.next = null;
    }
}

public class SocialMediaFriendManager {
    User head = null;

    void addUser(int userId, String name, int age) {
        User newUser = new User(userId, name, age);
        if (head == null) {
            head = newUser;
        } else {
            User temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newUser;
        }
    }

    User getUserById(int userId) {
        User temp = head;
        while (temp != null) {
            if (temp.userId == userId) return temp;
            temp = temp.next;
        }
        return null;
    }

    void addFriend(int userId1, int userId2) {
        User u1 = getUserById(userId1);
        User u2 = getUserById(userId2);
        if (u1 != null && u2 != null && userId1 != userId2) {
            if (!u1.friendIds.contains(userId2)) u1.friendIds.add(userId2);
            if (!u2.friendIds.contains(userId1)) u2.friendIds.add(userId1);
        }
    }

    void removeFriend(int userId1, int userId2) {
        User u1 = getUserById(userId1);
        User u2 = getUserById(userId2);
        if (u1 != null && u2 != null) {
            u1.friendIds.remove(Integer.valueOf(userId2));
            u2.friendIds.remove(Integer.valueOf(userId1));
        }
    }

    void displayFriends(int userId) {
        User user = getUserById(userId);
        if (user != null) {
            System.out.println("Friends of " + user.name + ":");
            for (int id : user.friendIds) {
                User friend = getUserById(id);
                if (friend != null) {
                    System.out.println("ID: " + friend.userId + ", Name: " + friend.name);
                }
            }
        }
    }

    void mutualFriends(int userId1, int userId2) {
        User u1 = getUserById(userId1);
        User u2 = getUserById(userId2);
        if (u1 != null && u2 != null) {
            System.out.println("Mutual Friends:");
            for (int id : u1.friendIds) {
                if (u2.friendIds.contains(id)) {
                    User mutual = getUserById(id);
                    if (mutual != null)
                        System.out.println("ID: " + mutual.userId + ", Name: " + mutual.name);
                }
            }
        }
    }

    void searchUser(String nameOrId) {
        User temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(nameOrId) || String.valueOf(temp.userId).equals(nameOrId)) {
                System.out.println("User Found: ID: " + temp.userId + ", Name: " + temp.name + ", Age: " + temp.age);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("User not found.");
    }

    void countFriends() {
        User temp = head;
        while (temp != null) {
            System.out.println("User: " + temp.name + ", Total Friends: " + temp.friendIds.size());
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        SocialMediaFriendManager manager = new SocialMediaFriendManager();
        Scanner sc = new Scanner(System.in);

        manager.addUser(1, "Alice", 25);
        manager.addUser(2, "Bob", 28);
        manager.addUser(3, "Charlie", 30);
        manager.addUser(4, "David", 27);

        manager.addFriend(1, 2);
        manager.addFriend(1, 3);
        manager.addFriend(2, 3);
        manager.addFriend(3, 4);

        manager.displayFriends(1);
        manager.mutualFriends(1, 2);
        manager.searchUser("Charlie");
        manager.countFriends();
        manager.removeFriend(1, 2);
        manager.displayFriends(1);
    }
}
