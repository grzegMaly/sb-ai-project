import {useState} from "react";

export const ChatComponent = () => {

    const [prompt, setPrompt] = useState('');
    const [chatResponse, setChatresponse] = useState('');

    const askAI = async () => {
            try {
                const response = await fetch(`http://localhost:8080/ask-ai?prompt=${prompt}`)
                const data = await response.text();
                setChatresponse(data);
            } catch (error) {
                console.error("Error generating output: ", error)
            }
    }
    return (
        <div>
            <h2>Talk to AI</h2>
            <input
                type="text"
                value={prompt}
                onChange={e => setPrompt(e.target.value)}
                placeholder="Enter a prompt for AI"
            />
            <button onClick={askAI}>Ask AI</button>
            <div className="output">
                <p>{chatResponse}</p>
            </div>
        </div>
    )
}