import random


def generate_random_number(low, high):
    if low > high:
        raise ValueError("Lower limit should be less than or equal to the upper limit.")
    return random.randint(low, high)


def evaluate_guess(guess, target_number):
    if target_number == guess:
        return "correct"
    if guess > target_number:
        return "too_high"
    return "too_low"


def play_number_game(low, high, target_number=None, max_attempts=10, input_func=input):
    if low > high:
        raise ValueError("Lower limit should be less than or equal to the upper limit.")

    if target_number is None:
        target_number = generate_random_number(low, high)

    attempts_used = 0

    while attempts_used < max_attempts:
        raw_guess = input_func("Guess the number: ")
        try:
            guess = int(raw_guess)
        except ValueError:
            print("Please enter a valid integer.")
            continue

        attempts_used += 1
        result = evaluate_guess(guess, target_number)

        if result == "correct":
            return {"status": "won", "target": target_number, "attempts_used": attempts_used}
        if result == "too_high":
            print("The number is too high! Try lower.")
        else:
            print("The number is too low! Try higher.")

    return {"status": "lost", "target": target_number, "attempts_used": attempts_used}


def main():
    print("Welcome to the number guessing game!")
    print("You have 10 chances to guess the number.")
    print("Let's start the game!")

    try:
        low = int(input("Enter the lower limit of the range: "))
        high = int(input("Enter the upper limit of the range: "))
    except ValueError:
        print("Please enter valid integer values.")
        return

    try:
        result = play_number_game(low, high, max_attempts=10)
    except ValueError as exc:
        print(exc)
        return

    if result["status"] == "won":
        print(f"Correct! The number is {result['target']}. You guessed it in {result['attempts_used']} chances.")
    else:
        print(f"You lost! The number was {result['target']}.")


if __name__ == "__main__":
    main()
