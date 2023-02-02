import { Tweet } from "./tweet";
import { User } from "./user";

export class Reply{
    replyId : number;
    replyContent: string;
    repliedBy:User;
    tweet:Tweet;
}