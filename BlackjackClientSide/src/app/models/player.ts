import { Card } from "./card";
import { User } from "./user";
import { Room } from "./room";

export class Player {
    id: number;
    playerHand: Array<Card>;
    lastName: string;
    user: User;
    gameRoom: Room;
}