export default function Button({onCange, str, description}){
    return(
    <button onClick={() =>onCange(str)}>{description}</button>
    )
}