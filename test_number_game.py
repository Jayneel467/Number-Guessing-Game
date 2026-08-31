import importlib.util
from pathlib import Path

module_path = Path(__file__).with_name("Number_game.py")
spec = importlib.util.spec_from_file_location("number_game_module", module_path)
number_game = importlib.util.module_from_spec(spec)
spec.loader.exec_module(number_game)


def test_generate_random_number_in_range():
    number = number_game.generate_random_number(1, 10)
    assert 1 <= number <= 10


def test_evaluate_guess_correct():
    assert number_game.evaluate_guess(7, 7) == "correct"
    assert number_game.evaluate_guess(9, 7) == "too_high"
    assert number_game.evaluate_guess(5, 7) == "too_low"


def test_play_number_game_wins_on_first_try():
    outputs = []

    def fake_input(prompt):
        outputs.append(prompt)
        return "7"

    result = number_game.play_number_game(1, 10, target_number=7, max_attempts=3, input_func=fake_input)
    assert result["status"] == "won"
    assert result["attempts_used"] == 1
