package com.example;

import java.util.Random;

public class JavaJokes {

    public String getRandomJoke() {

        String jokeOne = "\n" +
                "Doctor: \"I'm sorry but you suffer from a terminal illness and have only 10 to live.\"\n" +
                "\n" +
                "Patient: \"What do you mean, 10? 10 what? Months? Weeks?!\"\n" +
                "\n" +
                "Doctor: \"Nine.\"";

        String jokeTwo = "A man asks a farmer near a field, “Sorry sir, would you mind if I crossed your field instead of going around it? You see, I have to catch the 4:23 train.”\n" +
                "\n" +
                "The farmer says, “Sure, go right ahead. And if my bull sees you, you’ll even catch the 4:11 one.”";

        String jokeThree = "Mother: \"How was school today, Patrick?\"\n" +
                "\n" +
                "Patrick: \"It was really great mum! Today we made explosives!\"\n" +
                "\n" +
                "Mother: \"Ooh, they do very fancy stuff with you these days. And what will you do at school tomorrow?\"\n" +
                "\n" +
                "Patrick: \"What school?\"";

        String[] jokes = new String[]{jokeOne, jokeTwo, jokeThree};

        Random random = new Random();

        return jokes[random.nextInt(3)];

    }

}
