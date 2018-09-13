import { Player } from "./player";
import { Card } from "./card";

export class Room {
    id: number;
    maxPlayers: number;
    currentState: string;
    playersInRoom: Array<Player>;
    cards: Array<Card>;
}