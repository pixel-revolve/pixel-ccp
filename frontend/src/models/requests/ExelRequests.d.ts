export type ExelAddRequest = {
    evaluatorId:number
    exelName:string
    content:string
    description:string
}
export type StopQuestionnaireRequest = {
    id:string,
    isStop:number
}