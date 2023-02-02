import { Reply } from "./reply";
import { User } from "./user";

export class Tweet{
    tweetId ?: number;
    tweetContent: string;
    reply ?: Reply[];
    user : User;
    likedBy ?= new Set<string>();
}