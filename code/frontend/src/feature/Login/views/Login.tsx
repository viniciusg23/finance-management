import React from 'react'
import { useLogin } from '../hooks/useLogin';

export const Login = () => {
    const {
        email,
        setEmail,
        password,
        setPassword,
        error,
        setError,
        handleLogin
    } = useLogin();

    return (
        <div className="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
            <div className="sm:mx-auto sm:w-full sm:max-w-sm">
                <img className="mx-auto h-10 w-auto" src="https://tailwindui.com/img/logos/mark.svg?color=green&shade=600" alt="Your Company" />
                <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Entre com sua conta</h2>
            </div>

            <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                <form className="space-y-6" method="POST" onSubmit={(event) => handleLogin(event, email, password, setError)}>
                    <div>
                        <label htmlFor="email" className="block text-sm font-medium leading-6 text-gray-900">Endereço de e-mail</label>
                        <div className="mt-2">
                            <input id="email" value={email} onChange={e => setEmail(e.target.value)} name="email" type="email" autoComplete="email" required className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-black placeholder:text-gray-400 focus:ring-2 focus:ring-mediumGreen sm:text-sm sm:leading-6 pl-3" />
                        </div>
                    </div>

                    <div>
                        <div className="flex items-center justify-between">
                            <label htmlFor="password" className="block text-sm font-medium leading-6 text-gray-900">Senha</label>
                        </div>
                        <div className="mt-2">
                            <input id="password" value={password} onChange={e => setPassword(e.target.value)} name="password" type="password" autoComplete="current-password" required className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-black placeholder:text-gray-400 focus:ring-2 focus:ring-mediumGreen sm:text-sm sm:leading-6 pl-3" />
                        </div>
                    </div>

                    <div className='justify-center'>
                        <button type="submit" className="flex w-full justify-center rounded-md bg-mediumGreen px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-green focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green">entrar</button>
                        {error && <p className="w-full mt-2 text-red-500 text-center font-bold">{error}</p>}
                    </div>
                </form>
            </div>
        </div>
    );
}
