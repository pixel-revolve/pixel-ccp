export type TeamAddRequest = {
    evaluatorId:number,
    teamName:string,
    teamNumber:number,
    teamCycle:number,
    teamDescription:string
}

export type TeamUpdateRequest = {
    teamId:number,
    teamNumber:number,
    teamCycle:number,
    description:string,
    teamName:string
}
