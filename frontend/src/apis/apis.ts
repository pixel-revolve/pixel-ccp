import {myAxios} from "../utils/MyAnxios";
import {EvaluatorLoginRequest} from "../models/requests/EvaluatorRequests";

import {TeamAddRequest, TeamUpdateRequest} from "../models/requests/TeamRequests";
import {TeamMemberUpdateRequest} from "../models/requests/TeamMemberRequests";
import {ExelAddRequest, StopQuestionnaireRequest} from "../models/requests/ExelRequests";


export const loginRequest = async (postData : EvaluatorLoginRequest) => {
    return await myAxios.post("/evaluator/login", postData);
}

export const getCurrentUserRequest = async (token: string) => {
    return await myAxios.get("/evaluator/current");
}


export const addTeamRequest = async (teamAddRequest: TeamAddRequest) => {
    return await myAxios.post("/team/add", teamAddRequest);
}

export const listTeamsRequest = async (evaluatorId: number) => {
    return await myAxios.get("/team/list", {
        params: {
            evaluatorId: evaluatorId
        }
    })
}

export const deleteTeamRequest = async (teamId: number) => {
    return await myAxios.get("/team/delete", {
        params: {
            teamId: teamId
        }
    })
}
export const updateTeamRequest = async (teamUpdateRequest: TeamUpdateRequest) => {
    return await myAxios.post("/team/update", teamUpdateRequest);
}

export const listTeamMembersRequest = async (teamId: number) => {
    return await myAxios.get("/teamMember/list", {
            params: {
                teamId: teamId
            }
        }
    )
}

export const updateTeamMemberRequest = async (teamMemberUpdateRequest: TeamMemberUpdateRequest) => {
    return await myAxios.post("/teamMember/update", teamMemberUpdateRequest);
}

export const deleteTeamMemberRequest = async (id:number) => {
    return await myAxios.get("/teamMember/delete",{
        params: {
            id: id
        }
    } );
}
export const addExelRequest = async (exel:ExelAddRequest)  =>{
    return await myAxios.post("/exel/save",exel);
}

export const listExelRequest = async (evaluatorId:number) =>{
    return await myAxios.get("/exel/list",{
        params:{
            evaluatorId:evaluatorId
        }
    })
}

export const changeQuestionnaireStatusRequest = async (stopQuestionnaireRequest: StopQuestionnaireRequest) => {
    return await myAxios.post("/exel/stop", stopQuestionnaireRequest);
}

export const deleteQuestionnaireRequest = async (id: string) => {
    return await myAxios.get("/exel/delete", {
        params:{
            id:id
        }
    });
}
